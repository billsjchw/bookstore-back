package com.example.bookstore.entity;

import java.io.Serializable;

public class ItemId implements Serializable {
    Long order;
    String book;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ItemId))
            return false;
        ItemId itemId = (ItemId) obj;
        return order.equals(itemId.order) && book.equals(itemId.book);
    }

    @Override
    public int hashCode() {
        return (int) (order + book.hashCode());
    }

    public Long getOrder() {
        return order;
    }

    public void setOrder(Long orderId) {
        this.order = orderId;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
}
