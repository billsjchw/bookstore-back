package com.example.bookstore.service;

import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private @Autowired UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Status register(User user) {
        if (userDao.existsByUsername(user.getUsername()))
            return Status.DUP_USERNAME;
        user.setBanned(false);
        user.setCart(new Cart());
        userDao.save(user);
        return Status.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Status ban(String username) {
        if (!userDao.existsByUsername(username))
            return Status.USER_NOT_FOUND;
        User user = userDao.findByUsername(username);
        user.setBanned(true);
        userDao.save(user);
        return Status.SUCCESS;
    }

    @Override
    public Status free(String username) {
        if (!userDao.existsByUsername(username))
            return Status.USER_NOT_FOUND;
        User user = userDao.findByUsername(username);
        user.setBanned(false);
        userDao.save(user);
        return Status.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<User> findAll() {
        return userDao.findAll();
    }
}
