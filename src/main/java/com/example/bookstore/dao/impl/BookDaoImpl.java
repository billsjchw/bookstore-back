package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Cover;
import com.example.bookstore.entity.Introduction;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CoverRepository;
import com.example.bookstore.repository.IntroductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired BookRepository bookRepo;
    @Autowired CoverRepository coverRepo;
    @Autowired IntroductionRepository introRepo;

    @Override
    public boolean existsByIsbn(String isbn) {
        return bookRepo.existsById(isbn);
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = bookRepo.findAllByOrderByIsbnAsc();
        List<Cover> covers = coverRepo.findAllByOrderByIsbn();
        List<Introduction> introductions = introRepo.findAllByOrderByIsbn();
        int size = books.size();
        for (int i = 0; i < size; ++i) {
            Book book = books.get(i);
            Cover cover = covers.get(i);
            Introduction intro = introductions.get(i);
            book.complete(cover, intro);
        }
        return books;
    }

    @Override
    public Book getByIsbn(String isbn) {
        Book book = bookRepo.findById(isbn).orElse(null);
        Cover cover = coverRepo.findById(isbn).orElse(null);
        Introduction intro = introRepo.findById(isbn).orElse(null);
        if (book == null || cover == null || intro == null)
            return null;
        book.complete(cover, intro);
        return book;
    }

    @Override
    public void save(Book book) {
        Cover cover = new Cover(book);
        Introduction intro = new Introduction(book);
        bookRepo.save(book);
        coverRepo.save(cover);
        introRepo.save(intro);
    }
}
