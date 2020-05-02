package com.example.bookstore.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("introductions")
public class Introduction {
    private String isbn;
    private String data;

    public Introduction(Book book) {
        this.isbn = book.getIsbn();
        this.data = book.getIntroduction();
    }

    @Id
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Field("data")
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
