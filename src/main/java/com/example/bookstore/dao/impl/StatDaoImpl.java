package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.StatDao;
import com.example.bookstore.dto.Item;
import com.example.bookstore.dto.PersonalStat;
import com.example.bookstore.dto.TopConsumer;
import com.example.bookstore.dto.TopSeller;
import com.example.bookstore.entity.*;
import com.example.bookstore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class StatDaoImpl implements StatDao {
    @Autowired private BookRepository bookRepo;
    @Autowired private CoverRepository coverRepo;
    @Autowired private IntroductionRepository introRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private AvatarRepository avatarRepo;
    @Autowired private OrderRepository orderRepo;

    @Override
    public List<TopSeller> findTopSellers(int n, String timePlacedStart, String timePlacedEnd) {
        List<Map<String, Object>> tuples =
                orderRepo.calcTopSellers(n, timePlacedStart, timePlacedEnd);
        List<TopSeller> topSellers = new ArrayList<>();
        for (Map<String, Object> tuple : tuples) {
            int bookId = (int) tuple.get("book");
            int totalAmount = ((BigDecimal) tuple.get("total_amount")).intValue();
            Book book = bookRepo.findById(bookId).orElse(null);
            completeBook(book);
            topSellers.add(new TopSeller(book, totalAmount));
        }
        return topSellers;
    }

    @Override
    public List<TopConsumer> findTopConsumers(int n, String timePlacedStart, String timePlacedEnd) {
        List<Map<String, Object>> tuples =
                orderRepo.calcTopConsumers(n, timePlacedStart, timePlacedEnd);
        List<TopConsumer> topConsumers = new ArrayList<>();
        for (Map<String, Object> tuple : tuples) {
            int userId = (int) tuple.get("user");
            int totalPrice = ((BigDecimal) tuple.get("total_price")).intValue();
            User user = userRepo.findById(userId).orElse(null);
            completeUser(user);
            topConsumers.add(new TopConsumer(user, totalPrice));
        }
        return topConsumers;
    }

    @Override
    public PersonalStat calcMyPersonalStat(int userId, String timePlacedStart, String timePlacedEnd) {
        int totalAmount = orderRepo.calcMyTotalAmount(userId, timePlacedStart, timePlacedEnd);
        int totalPrice = orderRepo.calcMyTotalPrice(userId, timePlacedStart, timePlacedEnd);
        List<Map<String, Object>> tuples =
                orderRepo.calcMyItems(userId, timePlacedStart, timePlacedEnd);
        List<Item> items = new ArrayList<>();
        for (Map<String, Object> tuple : tuples) {
            int bookId = (int) tuple.get("book");
            int amount = ((BigDecimal) tuple.get("amount")).intValue();
            Book book = bookRepo.findById(bookId).orElse(null);
            completeBook(book);
            items.add(new Item(book, amount));
        }
        return new PersonalStat(totalAmount, totalPrice, items);
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
