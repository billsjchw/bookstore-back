package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.CartDao;
import com.example.bookstore.entity.*;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.CoverRepository;
import com.example.bookstore.repository.IntroductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired private CartRepository cartRepository;
    @Autowired private CoverRepository coverRepository;
    @Autowired private IntroductionRepository introductionRepository;

    @Override
    public Cart findOneByUserId(int userId) {
        Cart cart = cartRepository.findOneByUserId(userId).orElse(null);
        if (cart == null)
            return null;
        for (CartItem item : cart.getItems())
            completeBook(item.getBook());
        return cart;
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    private void completeBook(Book book) {
        if (book == null)
            return;
        int bookId = book.getId();
        Cover cover = coverRepository.findById(bookId).orElse(null);
        Introduction introduction = introductionRepository.findById(bookId).orElse(null);
        book.setCover(cover);
        book.setIntroduction(introduction);
    }
}
