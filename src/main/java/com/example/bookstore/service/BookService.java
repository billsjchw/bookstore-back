package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.dto.Message;

public interface BookService {
    Message findBookById(int id);
    Message findAllBooks(int page, int size);
    Message BookFuzzySearch(String keyword, int page, int size);
    Message addBook(Book book);
    Message editBook(Book book);
    Message deleteBookById(int id);
}
