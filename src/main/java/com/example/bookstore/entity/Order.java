package com.example.bookstore.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "`orders`")
@Access(AccessType.FIELD)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "`id`")
    private Integer id;

    @Basic
    @Column(name = "`user`")
    private Integer userId;

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

    public Order(Integer userId, Consignee consignee, Set<OrderItem> items) {
        this.userId = userId;
        this.consignee = consignee;
        this.items = items;
        this.status = "UNPAID";
        this.timePlaced = new Timestamp(System.currentTimeMillis());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
