package com.example.bookstore.entity;

import javax.persistence.Embeddable;

@Embeddable
public class CartItem {
    private String book;
    private int num;

    public String getBook() { return book; }

    public void setBook(String book) { this.book = book; }

    public int getNum() { return num; }

    public void setNum(int num) { this.num = num; }
}
