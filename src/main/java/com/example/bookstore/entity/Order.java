package com.example.bookstore.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "`orders`")
@Access(AccessType.FIELD)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "`id`")
    private Long id;

    @Basic
    @Column(name = "`user`")
    private String username;

    @Embedded
    @AttributeOverride(name = "address", column = @Column(name = "`address`"))
    @AttributeOverride(name = "phone", column = @Column(name = "`phone`"))
    @AttributeOverride(name = "name", column = @Column(name = "`name`"))
    private Consignee consignee;

    @Basic
    @Column(name = "`status`")
    private String status;

    @Basic
    @Column(name = "`time_placed`")
    private Timestamp timePlaced;

    @Basic
    @Column(name = "`time_paid`")
    private Timestamp timePaid;

    @Basic
    @Column(name = "`time_done`")
    private Timestamp timeDone;

    @ElementCollection
    @CollectionTable(
            name = "`order_items`",
            joinColumns = @JoinColumn(name = "`order`", referencedColumnName = "`id`")
    )
    private Set<OrderItem> items;

    public Order() {}

    public Order(String username, Consignee consignee, Set<OrderItem> items) {
        this.username = username;
        this.consignee = consignee;
        this.items = items;
        this.status = "UNPAID";
        this.timePlaced = new Timestamp(System.currentTimeMillis());
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

    public Consignee getConsignee() {
        return consignee;
    }

    public void setConsignee(Consignee consignee) {
        this.consignee = consignee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(Timestamp timePlaced) {
        this.timePlaced = timePlaced;
    }

    public Timestamp getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(Timestamp timePaid) {
        this.timePaid = timePaid;
    }

    public Timestamp getTimeDone() {
        return timeDone;
    }

    public void setTimeDone(Timestamp timeDone) {
        this.timeDone = timeDone;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }
}
