package com.example.bookstore.service.impl;

import com.example.bookstore.dao.CartDao;
import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.Authority;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.User;
import com.example.bookstore.misc.BookstoreUserDetails;
import com.example.bookstore.service.UserService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired private UserDao userDao;
    @Autowired private CartDao cartDao;

    @Override
    public Message register(User user) {
        if (userDao.existsByUsername(user.getUsername()))
            return new Message("DUP_USERNAME", null);
        user.setEnabled(true);
        user.setRoles(new HashSet<>());
        user = userDao.saveAndFlush(user);
        Cart cart = new Cart(user.getId());
        cartDao.save(cart);
        return new Message("SUCCESS", user);
    }

    @Override
    public Message findAllUsers(int page, int size) {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        if (!user.getAuthorities().contains(new Authority(Authority.AuthorityId.USER_ADMIN)))
            return new Message("REJECTED", null);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<User> users = userDao.findAll(pageable);
        return new Message("SUCCESS", users);
    }

    @Override
    public Message userFuzzySearch(String keyword, int page, int size) {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        if (!user.getAuthorities().contains(new Authority(Authority.AuthorityId.USER_ADMIN)))
            return new Message("REJECTED", null);
        Example<User> example = Example.of(
                new User(keyword),
                ExampleMatcher
                        .matchingAny()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        );
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
        Page<User> users = userDao.findAll(example, pageable);
        return new Message("SUCCESS", users);
    }

    @Override
    public Message setUserEnabled(int userId, boolean enabled) {
        User client = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        if (!client.getAuthorities().contains(new Authority(Authority.AuthorityId.USER_ADMIN)))
            return new Message("REJECTED", null);
        User user = userDao.findById(userId);
        if (user == null)
            return new Message("USER_NOT_FOUND", null);
        user.setEnabled(enabled);
        userDao.saveAndFlush(user);
        return new Message("SUCCESS", null);
    }
}
