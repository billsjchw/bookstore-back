package com.example.bookstore.service;

import com.example.bookstore.util.Message;

public interface CartService {
    Message findMyCart();
    Message addToCart(String isbn, int increment);
}
