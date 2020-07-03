package com.example.bookstore.dto;

import java.util.List;

public class PersonalStat {
    private int totalAmount;
    private int totalPrice;
    private List<Item> items;

    public PersonalStat(int totalAmount, int totalPrice, List<Item> items) {
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
