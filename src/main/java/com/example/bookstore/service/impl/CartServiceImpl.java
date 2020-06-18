package com.example.bookstore.service.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.CartDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.CartItem;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.CartService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl implements CartService {
    @Autowired private CartDao cartDao;
    @Autowired private BookDao bookDao;

    @Override
    public Message addBookToMyCart(int bookId) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (!bookDao.existById(bookId))
            return new Message("BOOK_NOT_FOUND", null);
        Cart cart = cartDao.findOneByUserId(user.getId());
        CartItem oldCartItem = cart.getItem(bookId);
        Book book = new Book(bookId);
        int amount = oldCartItem == null ? 1 : oldCartItem.getAmount() + 1;
        boolean active = oldCartItem == null ? true : oldCartItem.getActive();
        if (amount > CartItem.MAX_AMOUNT)
            return new Message("MAX_AMOUNT_EXCEEDED", null);
        CartItem newCartItem = new CartItem(book, amount, active);
        if (oldCartItem != null)
            cart.getItems().remove(oldCartItem);
        cart.getItems().add(newCartItem);
        cartDao.save(cart);
        return new Message("SUCCESS", newCartItem);
    }

    @Override
    public Message findItemInMyCart(int bookId) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Cart cart = cartDao.findOneByUserId(user.getId());
        CartItem cartItem = cart.getItem(bookId);
        if (cartItem == null)
            return new Message("ITEM_NOT_FOUND", null);
        else
            return new Message("SUCCESS", cartItem);
    }

    @Override
    public Message findMyCart() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Cart cart = cartDao.findOneByUserId(user.getId());
        return new Message("SUCCESS", cart);
    }

    @Override
    public Message editItemInMyCart(CartItem cartItem) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Cart cart = cartDao.findOneByUserId(user.getId());
        if (!cart.getItems().contains(cartItem))
            return new Message("ITEM_NOT_FOUND", null);
        if (cartItem.getAmount() < CartItem.MIN_AMOUNT)
            return new Message("MIN_AMOUNT_EXCEEDED", null);
        if (cartItem.getAmount() > CartItem.MAX_AMOUNT)
            return new Message("MAX_AMOUNT_EXCEEDED", null);
        cart.getItems().remove(cartItem);
        cart.getItems().add(cartItem);
        cartDao.save(cart);
        return new Message("SUCCESS", null);
    }

    @Override
    public Message removeItemFromMyCart(int bookId) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Cart cart = cartDao.findOneByUserId(user.getId());
        cart.getItems().remove(new CartItem(new Book(bookId), 1, true));
        cartDao.save(cart);
        return new Message("SUCCESS", null);
    }
}
