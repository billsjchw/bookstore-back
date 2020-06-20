package com.example.bookstore.dao;

import com.example.bookstore.entity.Cart;

public interface CartDao {
    Cart findOneByUserId(int userId);
    void save(Cart cart);
}
