package com.example.bookstore.service;

import com.example.bookstore.entity.Book;
import com.example.bookstore.util.Message;

public interface BookService {
    Message findAllBooks();
    Message getBookByIsbn(String isbn);
    Message addBook(Book book);
}
