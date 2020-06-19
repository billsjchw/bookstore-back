package com.example.bookstore.service.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.CartDao;
import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.entity.*;
import com.example.bookstore.misc.BookstoreUserDetails;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    @Autowired private CartDao cartDao;
    @Autowired private OrderDao orderDao;
    @Autowired private BookDao bookDao;

    @Override
    public Message orderItemsInMyCart(Consignee consignee, String paymentMethod) {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        Cart cart = cartDao.findOneByUserId(user.getId());
        Set<CartItem> activeCartItems = new HashSet<>();
        Set<CartItem> inactiveCartItems = new HashSet<>();
        for (CartItem cartItem : cart.getItems())
            if (cartItem.getActive())
                activeCartItems.add(cartItem);
            else
                inactiveCartItems.add(cartItem);
        if (activeCartItems.isEmpty())
            return new Message("NOTHING_TO_ORDER", null);
        for (CartItem cartItem : activeCartItems)
            if (cartItem.getAmount() > cartItem.getBook().getStock())
                return new Message("OUT_OF_STOCK", cartItem);
        for (CartItem cartItem : activeCartItems) {
            Book book = cartItem.getBook();
            book.setStock(book.getStock() - cartItem.getAmount());
            bookDao.saveAndFlush(book);
        }
        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem : activeCartItems) {
            Book book = cartItem.getBook();
            int amount = cartItem.getAmount();
            int price = book.getPrice();
            orderItems.add(new OrderItem(book, amount, price));
        }
        Order order = new Order(user, consignee, paymentMethod, orderItems);
        orderDao.save(order);
        cart.setItems(inactiveCartItems);
        cartDao.save(cart);
        return new Message("SUCCESS", null);
    }

    @Override
    public Message orderSearch(String timePlacedStart, String timePlacedEnd,
                               int page, int size) {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        if (!user.getAuthorities().contains(new Authority(Authority.AuthorityId.ORDER_ADMIN)))
            return new Message("REJECTED", null);
        Page<Order> orders = orderDao.orderSearch(timePlacedStart,
                timePlacedEnd, page, size);
        return new Message("SUCCESS", orders);
    }

    @Override
    public Message orderFuzzySearch(String keyword, String timePlacedStart,
                                    String timePlacedEnd, int page, int size) {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        if (!user.getAuthorities().contains(new Authority(Authority.AuthorityId.ORDER_ADMIN)))
            return new Message("REJECTED", null);
        Page<Order> orders = orderDao.orderFuzzySearch(keyword,
                timePlacedStart, timePlacedEnd, page, size);
        return new Message("SUCCESS", orders);
    }

    @Override
    public Message myOrderSearch(String timePlacedStart, String timePlacedEnd,
                                 int page, int size) {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        Page<Order> orders = orderDao.orderSearchWithUserId(user.getId(),
                timePlacedStart, timePlacedEnd, page, size);
        return new Message("SUCCESS", orders);
    }

    @Override
    public Message myOrderFuzzySearch(String keyword, String timePlacedStart,
                                      String timePlacedEnd, int page, int size) {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        Page<Order> orders = orderDao.orderFuzzySearchWithUserId(user.getId(),
                keyword, timePlacedStart, timePlacedEnd, page, size);
        return new Message("SUCCESS", orders);
    }
}
