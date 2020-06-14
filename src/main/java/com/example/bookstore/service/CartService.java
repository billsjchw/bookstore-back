package com.example.bookstore.service;

import com.example.bookstore.entity.CartItem;
import com.example.bookstore.util.Message;

public interface CartService {
    Message addBookToMyCart(int bookId);
    Message findItemInMyCart(int bookId);
    Message findMyCart();
    Message editItemInMyCart(CartItem cartItem);
    Message removeItemFromMyCart(int bookId);
}
