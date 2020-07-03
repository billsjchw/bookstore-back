package com.example.bookstore.service;

import com.example.bookstore.dto.Message;

public interface StatService {
    Message findTopSellers(int n, String timePlacedStart, String timePlacedEnd);
    Message findTopConsumers(int n, String timePlacedStart, String timePlacedEnd);
    Message calcMyPersonalStat(String timePlacedStart, String timePlacedEnd);
}
