package com.example.bookstore.service;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DealServiceImpl implements DealService {
    private @Autowired UserDao userDao;
    private @Autowired BookDao bookDao;
    private @Autowired OrderDao orderDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Status addToCart(String username, String isbn, int num) {
        if (!userDao.existsByUsername(username))
            return Status.USER_NOT_FOUND;
        if (!bookDao.existsByISBN(isbn))
            return Status.BOOK_NOT_FOUND;
        User user = userDao.findByUsername(username);
        List<CartItem> items = user.getCart().getItems();
        for (CartItem item : items)
            if (item.getBook().equals(isbn)) {
                item.setNum(item.getNum() + num);
                return Status.SUCCESS;
            }
        items.add(new CartItem(isbn, num));
        return Status.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Status placeOrder(String username) {
        if (!userDao.existsByUsername(username))
            return Status.USER_NOT_FOUND;
        User user = userDao.findByUsername(username);
        Cart cart = user.getCart();
        List<CartItem> items = cart.getItems();
        if (items.isEmpty())
            return Status.EMPTY_CART;
        for (CartItem item : items) {
            Book book = bookDao.findByISBN(item.getBook());
            if (book.getStock() < item.getNum())
                return Status.OUT_OF_STOCK;
        }
        orderDao.save(new Order(cart));
        user.setCart(new Cart());
        userDao.save(user);
        for (CartItem item : items) {
            Book book = bookDao.findByISBN(item.getBook());
            book.setStock(book.getStock() - item.getNum());
            bookDao.save(book);
        }
        return Status.SUCCESS;
    }
}
