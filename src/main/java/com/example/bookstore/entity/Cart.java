package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "`carts`")
@Access(value = AccessType.FIELD)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "`id`")
    private Integer id;

    @Basic
    @Column(name = "`user`")
    private Integer userId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "`cart_items`",
            joinColumns = @JoinColumn(name = "cart", referencedColumnName = "id")
    )
    private Set<CartItem> items;

    public Cart() {}

    public Cart(Integer userId) {
        this.userId = userId;
        this.items = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<CartItem> getItems() {
        return items;
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id.equals(cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @JsonIgnore
    public CartItem getItem(int bookId) {
        for (CartItem cartItem : items)
            if (cartItem.getBook().getId() == bookId)
                return cartItem;
        return null;
    }
}
