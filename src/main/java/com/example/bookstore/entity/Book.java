package com.example.bookstore.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book {
    private String isbn;
    private String title;
    private String author;
    private Integer price;
    private String press;
    private Date date;
    private String lang;
    private Integer stock;
    private String intro;
    private String cover;

    @Id
    @Column(name = "isbn")
    public String getISBN() { return isbn; }

    public void setISBN(String isbn) { this.isbn = isbn; }

    @Column(name = "title", nullable = false)
    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    @Column(name = "author", nullable = false)
    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    @Column(name = "price", nullable = false)
    public Integer getPrice() { return price; }

    public void setPrice(Integer price) { this.price = price; }

    @Column(name = "press", nullable = false)
    public String getPress() { return press; }

    public void setPress(String press) { this.press = press; }

    @Column(name = "date", nullable = false)
    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    @Column(name = "lang", nullable = false)
    public String getLang() { return lang; }

    public void setLang(String lang) { this.lang = lang; }

    @Column(name = "stock", nullable = false)
    public Integer getStock() { return stock; }

    public void setStock(Integer stock) { this.stock = stock; }

    @Column(name = "intro", nullable = false)
    public String getIntro() { return intro; }

    public void setIntro(String intro) { this.intro = intro; }

    @Column(name = "cover", nullable = false)
    public String getCover() { return cover; }

    public void setCover(String cover) { this.cover = cover; }
}
