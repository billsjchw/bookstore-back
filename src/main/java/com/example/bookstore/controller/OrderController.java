package com.example.bookstore.controller;

import com.example.bookstore.entity.Consignee;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired private OrderService orderService;

    @GetMapping(value = "/order/find-all-my-orders")
    Message findAllMyOrders() {
        return orderService.findAllMyOrders();
    }

    @PostMapping(value = "/order/place-order")
    Message placeOrder(@RequestBody Consignee consignee) {
        return orderService.placeOrder(consignee);
    }
}
