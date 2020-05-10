package com.example.bookstore.service;

import com.example.bookstore.entity.Consignee;
import com.example.bookstore.util.Message;

public interface OrderService {
    Message findAllMyOrders();
    Message placeOrder(Consignee consignee);
}
