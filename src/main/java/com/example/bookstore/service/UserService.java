package com.example.bookstore.service;

import com.example.bookstore.entity.User;
import com.example.bookstore.util.Message;

public interface UserService {
    Message register(User user);
    Message findAllUsers(int page, int size);
    Message userFuzzySearch(String keyword, int page, int size);
    Message setUserEnabled(int userId, boolean enabled);
}
