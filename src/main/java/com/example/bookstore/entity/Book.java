package com.example.bookstore.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "`books`")
@Access(value = AccessType.FIELD)
public class Book {
    @Id
    @Basic
    @Column(name = "isbn")
    private String isbn;

    @Basic
    @Column(name = "`title`")
    private String title;

    @Basic
    @Column(name = "`author`")
    private String author;

    @Basic
    @Column(name = "`language`")
    private String language;

    @Basic
    @Column(name = "`press`")
    private String press;

    @Basic
    @Column(name = "`date`")
    private Date date;

    @Basic
    @Column(name = "`price`")
    private Integer price;

    @Basic
    @Column(name = "`stock`")
    private Integer stock;

    @Transient
    private Cover cover;

    @Transient
    private Introduction introduction;

    public Book() {}

    public Book(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Cover getCover() {
        return cover;
    }

    public void setCover(Cover cover) {
        this.cover = cover;
    }

    public Introduction getIntroduction() {
        return introduction;
    }

    public void setIntroduction(Introduction introduction) {
        this.introduction = introduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isbn.equals(book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
