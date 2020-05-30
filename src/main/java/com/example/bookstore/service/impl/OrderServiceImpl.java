package com.example.bookstore.service.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.CartDao;
import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.entity.*;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl implements OrderService {
    @Autowired private OrderDao orderDao;
    @Autowired private CartDao cartDao;
    @Autowired private BookDao bookDao;

    @Override
    public Message findAllMyOrders() {
        int userId = ((User) SecurityContextHolder.getContext()
                .getAuthentication().getDetails()).getId();
        List<Order> orders = orderDao.findAllByUserId(userId);
        return new Message("SUCCESS", orders);
    }

    @Override
    public Message placeOrder(Consignee consignee) {
        int userId = ((User) SecurityContextHolder.getContext()
                .getAuthentication().getDetails()).getId();
        Cart cart = cartDao.findOneByUserId(userId);
        Set<CartItem> cartItems = cart.getItems();
        if (cartItems.isEmpty())
            return new Message("EMPTY_CART", null);
        for (CartItem cartItem : cartItems) {
            Book book = cartItem.getBook();
            if (cartItem.getAmount() > book.getStock())
                return new Message("OUT_OF_STOCK", book);
        }
        for (CartItem cartItem : cartItems) {
            Book book = cartItem.getBook();
            book.setStock(book.getStock() - cartItem.getAmount());
            bookDao.save(book);
        }
        Set<OrderItem> orderItems = new HashSet<>();
        for (CartItem cartItem : cartItems)
            orderItems.add(new OrderItem(cartItem.getBook(), cartItem.getAmount()));
        Order order = new Order(userId, consignee, orderItems);
        orderDao.save(order);
        cart.getItems().clear();
        cartDao.save(cart);
        return new Message("SUCCESS", null);
    }
}
