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

    @Embedded
    @AttributeOverride(name = "address", column = @Column(name = "`consignee_address`"))
    @AttributeOverride(name = "phone", column = @Column(name = "`consignee_phone`"))
    @AttributeOverride(name = "firstName", column = @Column(name = "`consignee_first_name`"))
    @AttributeOverride(name = "lastName", column = @Column(name = "`consignee_last_name`"))
    private Consignee consignee;

    @Basic
    @Column(name = "`time_placed`")
    private Timestamp timePlaced;

    @Basic
    @Column(name = "`payment_method`")
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "`user`", referencedColumnName = "`id`")
    private User user;

    @ElementCollection
    @CollectionTable(
            name = "`order_items`",
            joinColumns = @JoinColumn(name = "`order`", referencedColumnName = "`id`")
    )
    private Set<OrderItem> items;

    public Order() {}

    public Order(User user, Consignee consignee, String paymentMethod, Set<OrderItem> items) {
        this.user = user;
        this.consignee = consignee;
        this.paymentMethod = paymentMethod;
        this.items = items;
        this.timePlaced = new Timestamp(System.currentTimeMillis());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Consignee getConsignee() {
        return consignee;
    }

    public void setConsignee(Consignee consignee) {
        this.consignee = consignee;
    }

    public Timestamp getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(Timestamp timePlaced) {
        this.timePlaced = timePlaced;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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
