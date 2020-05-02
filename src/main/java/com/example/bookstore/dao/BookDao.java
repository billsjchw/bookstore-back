package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;

import java.util.List;

public interface BookDao {
    boolean existsByIsbn(String isbn);
    List<Book> findAll();
    void save(Book book);
}
