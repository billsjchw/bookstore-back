package com.example.bookstore.dao;

import com.example.bookstore.entity.User;

public interface UserDao {
    User findOneByUsername(String username);
}
