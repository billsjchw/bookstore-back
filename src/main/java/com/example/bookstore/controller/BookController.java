package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired BookService bookService;

    @GetMapping("/book/find-all-books")
    Message findAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping("/book/add-book")
    Message addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
}
