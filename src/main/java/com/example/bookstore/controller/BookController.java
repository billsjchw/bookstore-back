package com.example.bookstore.controller;

import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private @Autowired BookService bookService;

    @GetMapping(value = "/book/find")
    public Book find(@RequestParam(name = "isbn") String isbn) {
        return bookService.find(isbn);
    }

    @GetMapping(value = "/book/find-all")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @PutMapping(value = "/book/save")
    public void save(@RequestBody Book book) {
        bookService.save(book);
    }

    @DeleteMapping(value = "/book/delete")
    public void delete(@RequestParam(name = "isbn") String isbn) {
        bookService.delete(isbn);
    }
}
