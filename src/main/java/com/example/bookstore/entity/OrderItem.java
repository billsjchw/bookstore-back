package com.example.bookstore.entity;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
@Access(value = AccessType.FIELD)
public class OrderItem {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "`book`", referencedColumnName = "`id`")
    private Book book;

    @Basic
    @Column(name = "`amount`")
    private Integer amount;

    public OrderItem() {}

    public OrderItem(Book book, Integer amount) {
        this.book = book;
        this.amount = amount;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return book.equals(orderItem.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book);
    }
}
