package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.entity.Order;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired OrderRepository orderRepo;

    @Override
    public void save(Order order) {
        orderRepo.save(order);
    }
}
