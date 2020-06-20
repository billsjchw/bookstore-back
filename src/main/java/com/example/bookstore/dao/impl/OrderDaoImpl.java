package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.entity.*;
import com.example.bookstore.repository.AvatarRepository;
import com.example.bookstore.repository.CoverRepository;
import com.example.bookstore.repository.IntroductionRepository;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired private OrderRepository orderRepo;
    @Autowired private CoverRepository coverRepo;
    @Autowired private IntroductionRepository introRepo;
    @Autowired private AvatarRepository avatarRepo;

    @Override
    public Page<Order> orderSearch(String timePlacedStart, String timePlacedEnd, int page, int size) {
        List<Order> orders = orderRepo.orderSearch(timePlacedStart,
                timePlacedEnd, page, size);
        int total = orderRepo.orderSearchCount(timePlacedStart, timePlacedEnd);
        for (Order order : orders) {
            completeUser(order.getUser());
            for (OrderItem orderItem : order.getItems())
                completeBook(orderItem.getBook());
        }
        return new PageImpl<>(orders, PageRequest.of(page, size), total);
    }

    @Override
    public Page<Order> orderFuzzySearch(String keyword, String timePlacedStart,
                                        String timePlacedEnd, int page, int size) {
        List<Order> orders = orderRepo.orderFuzzySearch(keyword,
                timePlacedStart, timePlacedEnd, page, size);
        int total = orderRepo.orderFuzzySearchCount(keyword, timePlacedStart, timePlacedEnd);
        for (Order order : orders) {
            completeUser(order.getUser());
            for (OrderItem orderItem : order.getItems())
                completeBook(orderItem.getBook());
        }
        return new PageImpl<>(orders, PageRequest.of(page, size), total);
    }

    @Override
    public Page<Order> orderSearchWithUserId(int userId, String timePlacedStart,
                                             String timePlacedEnd, int page, int size) {
        List<Order> orders = orderRepo.orderSearchWithUserId(userId,
                timePlacedStart, timePlacedEnd, page, size);
        int total = orderRepo.orderSearchWithUserIdCount(userId, timePlacedStart, timePlacedEnd);
        for (Order order : orders) {
            completeUser(order.getUser());
            for (OrderItem orderItem : order.getItems())
                completeBook(orderItem.getBook());
        }
        return new PageImpl<>(orders, PageRequest.of(page, size), total);
    }

    @Override
    public Page<Order> orderFuzzySearchWithUserId(int userId, String keyword, String timePlacedStart,
                                                  String timePlacedEnd, int page, int size) {
        List<Order> orders = orderRepo.orderFuzzySearchWithUserId(userId, keyword,
                timePlacedStart, timePlacedEnd, page, size);
        int total = orderRepo.orderFuzzySearchWithUserIdCount(userId, keyword, timePlacedStart, timePlacedEnd);
        for (Order order : orders) {
            completeUser(order.getUser());
            for (OrderItem orderItem : order.getItems())
                completeBook(orderItem.getBook());
        }
        return new PageImpl<>(orders, PageRequest.of(page, size), total);
    }

    @Override
    public boolean bookIsOrdered(int bookId) {
        return orderRepo.bookIsOrdered(bookId) != 0;
    }

    @Override
    public void save(Order order) {
        orderRepo.save(order);
    }

    private void completeBook(Book book) {
        if (book == null)
            return;
        int bookId = book.getId();
        Cover cover = coverRepo.findById(bookId).orElse(null);
        Introduction intro = introRepo.findById(bookId).orElse(null);
        book.setCover(cover);
        book.setIntro(intro);
    }

    private void completeUser(User user) {
        if (user == null)
            return;
        int userId = user.getId();
        Avatar avatar = avatarRepo.findById(userId).orElse(null);
        user.setAvatar(avatar);
    }
}
