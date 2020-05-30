package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookDao {
    boolean existById(int id);
    Book findById(int id);
    List<Book> findAll();
    Page<Book> findAll(Pageable pageable);
    Page<Book> findAll(Example<Book> example, Pageable pageable);
    void save(Book book);
}
