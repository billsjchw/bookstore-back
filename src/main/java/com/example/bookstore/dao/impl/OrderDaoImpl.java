package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.entity.*;
import com.example.bookstore.repository.CoverRepository;
import com.example.bookstore.repository.IntroductionRepository;
import com.example.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired private OrderRepository orderRepo;
    @Autowired private CoverRepository coverRepo;
    @Autowired private IntroductionRepository introRepo;

    @Override
    public List<Order> findAllByUserId(int userId) {
        List<Order> orders = orderRepo.findAllByUserId(userId);
        for (Order order : orders)
            for (OrderItem item : order.getItems())
                completeBook(item.getBook());
        return orders;
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
}
