package com.example.bookstore.controller;

import com.example.bookstore.service.OrderService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired OrderService orderService;

    @PostMapping(value = "/order/add-to-cart")
    Message addToCart(@RequestParam String isbn, @RequestParam int inc) {
        return orderService.addToCart(isbn, inc);
    }

    @GetMapping(value = "/order/get-cart")
    Message getCart() {
        return orderService.getCart();
    }

    @GetMapping(value = "/order/get-history")
    Message getHistory() {
        return orderService.getHistory();
    }

    @PostMapping(value = "/order/place-order")
    Message placeOrder() {
        return orderService.placeOrder();
    }
}
