package com.example.bookstore.dao;

import com.example.bookstore.dto.PersonalStat;
import com.example.bookstore.dto.TopConsumer;
import com.example.bookstore.dto.TopSeller;

import java.util.List;

public interface StatDao {
    List<TopSeller> findTopSellers(int n, String timePlacedStart, String timePlacedEnd);
    List<TopConsumer> findTopConsumers(int n, String timePlacedStart, String timePlacedEnd);
    PersonalStat calcMyPersonalStat(int userId, String timePlacedStart, String timePlacedEnd);
}
