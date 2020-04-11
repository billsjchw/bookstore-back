package com.example.bookstore.dao;

import com.example.bookstore.entity.User;

import java.util.List;

public interface UserDao {
    boolean existsByUsername(String username);
    User findByUsername(String username);
    List<User> findAll();
    void save(User user);
}
