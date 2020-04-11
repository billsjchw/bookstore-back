package com.example.bookstore.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book {
    private String isbn;
    private String title;
    private String author;
    private int price;
    private String press;
    private Date date;
    private String lang;
    private String intro;
    private String cover;
    private int stock;

    @Id
    @Column(name = "isbn")
    public String getISBN() { return isbn; }

    public void setISBN(String isbn) { this.isbn = isbn; }

    @Basic
    @Column(name = "title", nullable = false)
    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    @Basic
    @Column(name = "author", nullable = false)
    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }

    @Basic
    @Column(name = "press", nullable = false)
    public String getPress() { return press; }

    public void setPress(String press) { this.press = press; }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    @Basic
    @Column(name = "lang", nullable = false)
    public String getLang() { return lang; }

    public void setLang(String lang) { this.lang = lang; }

    @Basic
    @Column(name = "intro", nullable = false)
    public String getIntro() { return intro; }

    public void setIntro(String intro) { this.intro = intro; }

    @Basic
    @Column(name = "cover", nullable = false)
    public String getCover() { return cover; }

    public void setCover(String cover) { this.cover = cover; }

    @Basic
    @Column(name = "stock", nullable = false)
    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }
}
