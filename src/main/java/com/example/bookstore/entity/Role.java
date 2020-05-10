package com.example.bookstore.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "`roles`")
@Access(value = AccessType.FIELD)
public class Role {
    @Id
    @Basic
    @Column(name = "`name`")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "`role_authority`",
            joinColumns = @JoinColumn(name = "`role`", referencedColumnName = "`name`"),
            inverseJoinColumns = @JoinColumn(name = "`authority`", referencedColumnName = "`name`")
    )
    private Set<Authority> authorities;

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
        Role role = (Role) o;
        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
