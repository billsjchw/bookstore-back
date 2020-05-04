package com.example.bookstore.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "`users`")
public class User {
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Boolean banned;
    private Boolean admin;
    private Order cart;
    private Set<Order> history;

    @Id
    @Column(name = "`username`")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "`password`")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "`email`")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Column(name = "`banned`")
    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    @Column(name = "`admin`")
    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "`cart`", referencedColumnName = "`id`")
    public Order getCart() {
        return cart;
    }

    public void setCart(Order cart) {
        this.cart = cart;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "`histories`",
            joinColumns = {@JoinColumn(name = "`user`", referencedColumnName = "`username`")},
            inverseJoinColumns = {@JoinColumn(name = "`order`", referencedColumnName = "`id`", unique = true)}
    )
    public Set<Order> getHistory() {
        return history;
    }

    public void setHistory(Set<Order> history) {
        this.history = history;
    }

    public void complete(Avatar avatar) {
        this.avatar = avatar.getData();
    }
}
