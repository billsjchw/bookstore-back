package com.example.bookstore.service;

import com.example.bookstore.entity.User;

import java.util.List;

public interface UserService {
    enum Status {SUCCESS, DUP_USERNAME, USER_NOT_FOUND};
    Status register(User user);
    Status ban(String username);
    Status free(String username);
    List<User> findAll();
}
