package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "`authorities`")
@Access(value = AccessType.FIELD)
public class Authority implements GrantedAuthority {
    public enum AuthorityId {BOOK_ADMIN, USER_ADMIN, ORDER_ADMIN};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "`id`")
    private Integer id;

    @Basic
    @Column(name = "`name`")
    private String name;

    public Authority() {}

    public Authority(AuthorityId id) {
        this.id = id.ordinal();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return id.equals(authority.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    @JsonIgnore
    public String getAuthority() {
        return name;
    }
}
