package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookDao {
    boolean existsById(int id);
    Book findById(int id);
    Page<Book> findAll(Pageable pageable);
    Page<Book> findAll(Example<Book> example, Pageable pageable);
    Book saveAndFlush(Book book);
    void deleteById(int id);
}
