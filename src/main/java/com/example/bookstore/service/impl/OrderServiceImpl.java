package com.example.bookstore.service.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Item;
import com.example.bookstore.entity.Order;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired UserDao userDao;
    @Autowired BookDao bookDao;
    @Autowired OrderDao orderDao;

    @Override
    public Message addToCart(String isbn, int inc) {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        if (!bookDao.existsByIsbn(isbn))
            return new Message("BOOK_NOT_FOUND", null);
        User user = userDao.getByUsername(username);
        Order cart = user.getCart();
        Map<String, Item> map = cart.getMap();
        if (map.containsKey(isbn)) {
            Item item = map.get(isbn);
            int num = item.getNum();
            if (num + inc < 0)
                return new Message("OVERFLOW", null);
            item.setNum(num + inc);
        } else
            cart.addItem(isbn, inc);
        orderDao.save(cart);
        return new Message("SUCCESS", null);
    }

    @Override
    public Message getCart() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User user = userDao.getByUsername(username);
        Order cart = user.getCart();
        return new Message("SUCCESS", cart);
    }

    @Override
    public Message getHistory() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User user = userDao.getByUsername(username);
        Set<Order> history = user.getHistory();
        return new Message("SUCCESS", history);
    }

    @Override
    public Message placeOrder() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User user = userDao.getByUsername(username);
        Order cart = user.getCart();
        if (cart.getItems().isEmpty())
            return new Message("EMPTY_CART", null);
        for (Item item : cart.getItems()) {
            int num = item.getNum();
            Book book = item.getBook();
            int stock = book.getStock();
            if (num > stock)
                return new Message("OUT_OF_STOCK", book);
        }
        for (Item item : cart.getItems()) {
            int num = item.getNum();
            Book book = item.getBook();
            int stock = book.getStock();
            book.setStock(stock - num);
            bookDao.save(book);
        }
        cart.setPlaced(true);
        cart.setTime(new Timestamp(System.currentTimeMillis()));
        orderDao.save(cart);
        user.setCart(new Order());
        user.getHistory().add(cart);
        userDao.save(user);
        return new Message("SUCCESS", null);
    }
}
