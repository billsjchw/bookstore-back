package com.example.bookstore.dao;

import com.example.bookstore.entity.Cart;

public interface CartDao {
    Cart findOneByUsername(String username);
    void save(Cart cart);
    void delete(Cart cart);
}
