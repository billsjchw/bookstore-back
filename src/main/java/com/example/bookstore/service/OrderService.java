package com.example.bookstore.service;

import com.example.bookstore.util.Message;

public interface OrderService {
    Message addToCart(String isbn, int inc);
    Message getCart();
    Message getHistory();
    Message placeOrder();
}
