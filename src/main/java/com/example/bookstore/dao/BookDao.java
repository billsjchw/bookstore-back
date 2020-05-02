package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;

public interface BookDao {
    boolean existsByIsbn(String isbn);
    void save(Book book);
}
