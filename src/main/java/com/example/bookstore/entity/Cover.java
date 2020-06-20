package com.example.bookstore.entity;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Document("covers")
@AccessType(value = AccessType.Type.FIELD)
public class Cover {
    @Id
    private Integer bookId;

    @Field(value = "data")
    private String data;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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
        Cover cover = (Cover) o;
        return bookId.equals(cover.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }
}
