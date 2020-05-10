package com.example.bookstore.dao;

import com.example.bookstore.entity.Order;

import java.util.List;

public interface OrderDao {
    List<Order> findAllByUsername(String username);
    void save(Order order);
}
