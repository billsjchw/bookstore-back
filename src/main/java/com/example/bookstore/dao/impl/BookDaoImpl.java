package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Cover;
import com.example.bookstore.entity.Introduction;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CoverRepository;
import com.example.bookstore.repository.IntroductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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
    public Book saveAndFlush(Book book) {
        Cover cover = book.getCover();
        Introduction intro = book.getIntro();
        book = bookRepo.saveAndFlush(book);
        int bookId = book.getId();
        cover.setBookId(bookId);
        intro.setBookId(bookId);
        coverRepo.save(cover);
        introRepo.save(intro);
        book.setCover(cover);
        book.setIntro(intro);
        return book;
    }

    @Override
    public void deleteById(int id) {
        bookRepo.deleteById(id);
        coverRepo.deleteById(id);
        introRepo.deleteById(id);
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
