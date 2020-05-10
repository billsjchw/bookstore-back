package com.example.bookstore.entity;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document(collection = "avatars")
@AccessType(value = AccessType.Type.FIELD)
public class Avatar {
    @Id
    private String username;

    @Field("data")
    private String data;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return username.equals(avatar.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
