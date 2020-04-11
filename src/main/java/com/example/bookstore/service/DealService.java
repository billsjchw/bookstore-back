package com.example.bookstore.service;

public interface DealService {
    enum Status {SUCCESS, USER_NOT_FOUND, BOOK_NOT_FOUND, EMPTY_CART, OUT_OF_STOCK};
    Status addToCart(String username, String isbn, int num);
    Status placeOrder(String username);
}
