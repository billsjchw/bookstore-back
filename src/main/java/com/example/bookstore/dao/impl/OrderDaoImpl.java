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
    @Autowired private OrderRepository orderRepository;
    @Autowired private CoverRepository coverRepository;
    @Autowired private IntroductionRepository introductionRepository;

    @Override
    public List<Order> findAllByUserId(int userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        for (Order order : orders)
            for (OrderItem item : order.getItems())
                completeBook(item.getBook());
        return orders;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    private void completeBook(Book book) {
        if (book == null)
            return;
        int bookId = book.getId();
        Cover cover = coverRepository.findById(bookId).orElse(null);
        Introduction introduction = introductionRepository.findById(bookId).orElse(null);
        book.setCover(cover);
        book.setIntroduction(introduction);
    }
}
