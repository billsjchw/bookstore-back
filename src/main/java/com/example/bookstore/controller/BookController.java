package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired private BookService bookService;

    @GetMapping("/book/find-book-by-id")
    Message findBookById(@RequestParam int id) {
        return bookService.findBookById(id);
    }

    @GetMapping("/book/find-all-books")
    Message findAllBooks(@RequestParam int page, @RequestParam int size) {
        return bookService.findAllBooks(page, size);
    }

    @GetMapping("/book/book-fuzzy-search")
    Message bookFuzzySearch(@RequestParam String keyword, @RequestParam int page, @RequestParam int size) {
        return bookService.BookFuzzySearch(keyword, page, size);
    }

    @PostMapping("/book/add-book")
    Message addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
}
