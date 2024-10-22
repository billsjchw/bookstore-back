package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "`users`")
@Access(value = AccessType.FIELD)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "`id`")
    private Integer id;

    @Basic
    @Column(name = "`username`")
    private String username;

    @Basic
    @Column(name = "`password`")
    private String password;

    @Embedded
    @AttributeOverride(name = "firstName", column = @Column(name = "`first_name`"))
    @AttributeOverride(name = "lastName", column = @Column(name = "`last_name`"))
    @AttributeOverride(name = "email", column = @Column(name = "`email`"))
    private Profile profile;

    @Basic
    @Column(name = "`enabled`")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "`user_role`",
            joinColumns = @JoinColumn(name = "`user`", referencedColumnName = "`id`"),
            inverseJoinColumns = @JoinColumn(name = "`role`", referencedColumnName = "`id`")
    )
    private Set<Role> roles;

    @Transient
    private Avatar avatar;

    public User() {}

    public User(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Set<Authority> getAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        for (Role role : roles)
            authorities.addAll(role.getAuthorities());
        return authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
