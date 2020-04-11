package com.example.bookstore.dao;

import com.example.bookstore.entity.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    private @Autowired BookRepository bookRepository;

    @Override
    public boolean existsByISBN(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public Book findByISBN(String isbn) {
        return bookRepository.findById(isbn).orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteByISBN(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
