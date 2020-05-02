package com.example.bookstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "items")
@IdClass(ItemId.class)
public class Item {
    Long order;
    Book book;
    Integer num;

    @Id
    @Column(name = "order")
    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "book", referencedColumnName = "isbn")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Column(name = "num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
