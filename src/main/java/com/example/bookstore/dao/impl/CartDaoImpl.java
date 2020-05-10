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
    public Cart findOneByUsername(String username) {
        Cart cart = cartRepository.findOneByUsername(username).orElse(null);
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

    @Override
    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }


    private void completeBook(Book book) {
        if (book == null)
            return;
        String isbn = book.getIsbn();
        Cover cover = coverRepository.findById(isbn).orElse(null);
        Introduction introduction = introductionRepository.findById(isbn).orElse(null);
        book.setCover(cover);
        book.setIntroduction(introduction);
    }
}
