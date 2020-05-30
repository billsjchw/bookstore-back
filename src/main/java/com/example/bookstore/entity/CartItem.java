package com.example.bookstore.entity;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
@Access(AccessType.FIELD)
public class CartItem {
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "`book`", referencedColumnName = "`id`")
    private Book book;

    @Basic
    @Column(name = "`amount`")
    private Integer amount;

    public CartItem() {}

    public CartItem(Book book, Integer amount) {
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
        CartItem cartItem = (CartItem) o;
        return book.equals(cartItem.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book);
    }
}
