package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "`orders`")
public class Order {
    private Long id;
    private Boolean placed;
    private Timestamp time;
    private Set<Item> items;

    public Order() {
        placed = false;
        items = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id`")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "`placed`")
    public Boolean getPlaced() {
        return placed;
    }

    public void setPlaced(Boolean placed) {
        this.placed = placed;
    }

    @Column(name = "`time`")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "`order`", referencedColumnName = "`id`")
    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @JsonIgnore
    @Transient
    public Map<String, Item> getMap() {
        Map<String, Item> map = new HashMap<>();
        for (Item item : items)
            map.put(item.getBook().getIsbn(), item);
        return map;
    }

    public void addItem(String isbn, int num) {
        Item item = new Item(id, new Book(isbn), num);
        items.add(item);
    }
}
