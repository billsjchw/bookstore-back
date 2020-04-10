package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;

import java.util.List;

public interface BookDao {
    Book findByISBN(String isbn);
    List<Book> findAll();
    void save(Book book);
    void deleteByISBN(String isbn);
}
