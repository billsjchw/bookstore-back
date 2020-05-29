package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.util.Message;

public interface BookService {
    Message findBookByIsbn(String isbn);
    Message findAllBooks(int page, int size);
    Message BookFuzzySearch(String keyword, int page, int size);
    Message addBook(Book book);
}
