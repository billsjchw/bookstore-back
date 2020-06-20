package com.example.bookstore.repository;

import com.example.bookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "call `OrderSearch`(?1, ?2, ?3, ?4)", nativeQuery = true)
    List<Order> orderSearch(String timePlacedStart, String timePlacedEnd, int page, int size);

    @Query(value = "call `OrderSearchCount`(?1, ?2)", nativeQuery = true)
    int orderSearchCount(String timePlacedStart, String timePlacedEnd);

    @Query(value = "call `OrderFuzzySearch`(?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    List<Order> orderFuzzySearch(String keyword, String timePlacedStart, String timePlacedEnd, int page, int size);

    @Query(value = "call `OrderFuzzySearchCount`(?1, ?2, ?3)", nativeQuery = true)
    int orderFuzzySearchCount(String keyword, String timePlacedStart, String timePlacedEnd);

    @Query(value = "call `OrderSearchWithUserId`(?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    List<Order> orderSearchWithUserId(int userId, String timePlacedStart, String timePlacedEnd, int page, int size);

    @Query(value = "call `OrderSearchWithUserIdCount`(?1, ?2, ?3)", nativeQuery = true)
    int orderSearchWithUserIdCount(int userId, String timePlacedStart, String timePlacedEnd);

    @Query(value = "call `OrderFuzzySearchWithUserId`(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    List<Order> orderFuzzySearchWithUserId(int userId, String keyword, String timePlacedStart,
                                           String timePlacedEnd, int page, int size);

    @Query(value = "call `OrderFuzzySearchWithUserIdCount`(?1, ?2, ?3, ?4)", nativeQuery = true)
    int orderFuzzySearchWithUserIdCount(int userId, String keyword, String timePlacedStart, String timePlacedEnd);

    @Query(value = "call `BookIsOrdered`(?1)", nativeQuery = true)
    int bookIsOrdered(int bookId);
}
