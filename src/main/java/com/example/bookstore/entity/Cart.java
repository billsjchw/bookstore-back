package com.example.bookstore.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    private Long id;
    private List<CartItem> items = new ArrayList<>();

    public Cart() {}

    public Cart(Cart cart) {
        id = cart.id;
        items = cart.items;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "cart_items",
            joinColumns = @JoinColumn(name = "cart", referencedColumnName = "id")
    )
    @AttributeOverride(name = "book", column = @Column(name = "book"))
    @AttributeOverride(name = "num", column = @Column(name = "num"))
    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
