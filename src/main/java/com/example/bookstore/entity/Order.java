package com.example.bookstore.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    Long id;
    Cart cart;
    Date time;

    public Order() {}

    public Order(Cart cart) {
        this.cart = cart;
        time = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() { return id; };

    public void setId(Long id) { this.id = id; }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart", referencedColumnName = "id")
    public Cart getCart() { return cart; }

    public void setCart(Cart cart) { this.cart = cart; }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time")
    public Date getTime() { return time; }

    public void setTime(Date time) { this.time = time; }
}
