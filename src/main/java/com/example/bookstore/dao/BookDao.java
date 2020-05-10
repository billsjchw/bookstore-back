package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;

import java.util.List;

public interface BookDao {
    boolean existByIsbn(String isbn);
    Book findByIsbn(String isbn);
    List<Book> findAll();
    void save(Book book);
}
