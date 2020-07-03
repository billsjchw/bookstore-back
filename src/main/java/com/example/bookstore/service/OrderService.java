package com.example.bookstore.service;

import com.example.bookstore.entity.Consignee;
import com.example.bookstore.dto.Message;

public interface OrderService {
    Message orderItemsInMyCart(Consignee consignee, String paymentMethod);
    Message orderSearch(String timePlacedStart, String timePlacedEnd, int page, int size);
    Message orderFuzzySearch(String keyword, String timePlacedStart, String timePlacedEnd, int page, int size);
    Message myOrderSearch(String timePlacedStart, String timePlacedEnd, int page, int size);
    Message myOrderFuzzySearch(String keyword, String timePlacedStart, String timePlacedEnd, int page, int size);
}
