package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private @Autowired BookService bookService;

    @GetMapping(value = "/book/get-one")
    public Book getOne(@RequestParam(name = "isbn") String isbn) {
        return bookService.findByISBN(isbn);
    }

    @GetMapping(value = "/book/get-all")
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @PutMapping(value = "/book/put")
    public void put(@RequestBody Book book) {
        bookService.save(book);
    }

    @DeleteMapping(value = "/book/delete")
    public void delete(@RequestParam(name = "isbn") String isbn) {
        bookService.deleteByISBN(isbn);
    }
}
