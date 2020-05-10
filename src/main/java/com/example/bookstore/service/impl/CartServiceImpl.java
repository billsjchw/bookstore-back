package com.example.bookstore.service.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.CartDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.CartItem;
import com.example.bookstore.service.CartService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl implements CartService {
    @Autowired private CartDao cartDao;
    @Autowired private BookDao bookDao;

    @Override
    public Message findMyCart() {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        Cart cart = cartDao.findOneByUsername(username);
        return new Message("SUCCESS", cart);
    }

    @Override
    public Message addToCart(String isbn, int increment) {
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        if (!bookDao.existByIsbn(isbn))
            return new Message("BOOK_NOT_FOUND", null);
        Cart cart = cartDao.findOneByUsername(username);
        Set<CartItem> items = cart.getItems();
        for (CartItem item : items)
            if (item.getBook().getIsbn().equals(isbn)) {
                int amount = item.getAmount();
                if (amount + increment < 0)
                    return new Message("OVERFLOW", null);
                item.setAmount(amount + increment);
                cartDao.save(cart);
                return new Message("SUCCESS", null);
            }
        items.add(new CartItem(new Book(isbn), increment));
        cartDao.save(cart);
        return new Message("SUCCESS", null);
    }
}
