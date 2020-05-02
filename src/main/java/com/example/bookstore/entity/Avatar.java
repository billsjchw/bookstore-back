package com.example.bookstore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "avatars")
public class Avatar {
    private String username;
    private String data;

    public Avatar() {}

    public Avatar(String username, String data) {
        this.username = username;
        this.data = data;
    }

    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Field("data")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
