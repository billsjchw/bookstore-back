package com.example.bookstore.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "`carts`")
@Access(value = AccessType.FIELD)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "`id`")
    private Long id;

    @Basic
    @Column(name = "`user`")
    private String username;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "`cart_items`",
            joinColumns = @JoinColumn(name = "cart", referencedColumnName = "id")
    )
    private Set<CartItem> items;

    public Cart() {}

    public Cart(String username) {
        this.username = username;
        this.items = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<CartItem> getItems() {
        return items;
    }

    public void setItems(Set<CartItem> items) {
        this.items = items;
    }
}
