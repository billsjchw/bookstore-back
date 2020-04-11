package com.example.bookstore.service;

import com.example.bookstore.entity.Book;

import java.util.List;

public interface BookService {
    Book find(String isbn);
    List<Book> findAll();
    void save(Book book);
    void delete(String isbn);
}
