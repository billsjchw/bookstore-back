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
    private Integer userId;

    @Field("data")
    private String data;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        return userId.equals(avatar.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
