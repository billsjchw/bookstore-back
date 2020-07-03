package com.example.bookstore.dto;

import com.example.bookstore.entity.Book;

public class TopSeller {
    private Book book;
    private int totalAmount;

    public TopSeller(Book book, int totalAmount) {
        this.book = book;
        this.totalAmount = totalAmount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
