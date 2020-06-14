package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Cover;
import com.example.bookstore.entity.Introduction;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CoverRepository;
import com.example.bookstore.repository.IntroductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired private BookRepository bookRepo;
    @Autowired private CoverRepository coverRepo;
    @Autowired private IntroductionRepository introRepo;

    @Override
    public boolean existById(int id) {
        return bookRepo.existsById(id);
    }

    @Override
    public Book findById(int id) {
        Book book = bookRepo.findById(id).orElse(null);
        completeBook(book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = bookRepo.findAll();
        for (Book book : books)
            completeBook(book);
        return books;
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        Page<Book> books = bookRepo.findAll(pageable);
        for (Book book : books)
            completeBook(book);
        return books;
    }

    @Override
    public Page<Book> findAll(Example<Book> example, Pageable pageable) {
        Page<Book> books = bookRepo.findAll(example, pageable);
        for (Book book : books)
            completeBook(book);
        return books;
    }

    @Override
    public void save(Book book) {
        book = bookRepo.saveAndFlush(book);
        int bookId = book.getId();
        Cover cover = book.getCover();
        cover.setBookId(bookId);
        Introduction intro = book.getIntro();
        intro.setBookId(bookId);
        coverRepo.save(cover);
        introRepo.save(intro);
    }

    private void completeBook(Book book) {
        if (book == null)
            return;
        int bookId = book.getId();
        Cover cover = coverRepo.findById(bookId).orElse(null);
        Introduction intro = introRepo.findById(bookId).orElse(null);
        book.setCover(cover);
        book.setIntro(intro);
    }
}
