package com.example.bookstore.dto;

import com.example.bookstore.entity.User;

public class TopConsumer {
    private User user;
    private int totalPrice;

    public TopConsumer(User user, int totalPrice) {
        this.user = user;
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
