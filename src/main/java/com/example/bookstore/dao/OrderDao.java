package com.example.bookstore.dao;

import com.example.bookstore.entity.Order;
import org.springframework.data.domain.Page;

public interface OrderDao {
    Page<Order> orderSearch(String timePlacedStart, String timePlacedEnd, int page, int size);
    Page<Order> orderFuzzySearch(String keyword, String timePlacedStart, String timePlacedEnd, int page, int size);
    Page<Order> orderSearchWithUserId(int userId, String timePlacedStart, String timePlacedEnd, int page, int size);
    Page<Order> orderFuzzySearchWithUserId(int userId, String keyword, String timePlacedStart,
                                           String timePlacedEnd, int page, int size);
    void save(Order order);
}
