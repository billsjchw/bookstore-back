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
    @Autowired private BookRepository bookRepository;
    @Autowired private CoverRepository coverRepository;
    @Autowired private IntroductionRepository introductionRepository;

    @Override
    public boolean existById(int id) {
        return bookRepository.existsById(id);
    }

    @Override
    public Book findById(int id) {
        Book book = bookRepository.findById(id).orElse(null);
        completeBook(book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = bookRepository.findAll();
        for (Book book : books)
            completeBook(book);
        return books;
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        for (Book book : books)
            completeBook(book);
        return books;
    }

    @Override
    public Page<Book> findAll(Example<Book> example, Pageable pageable) {
        Page<Book> books = bookRepository.findAll(example, pageable);
        for (Book book : books)
            completeBook(book);
        return books;
    }

    @Override
    public void save(Book book) {
        book = bookRepository.saveAndFlush(book);
        int bookId = book.getId();
        Cover cover = book.getCover();
        cover.setBookId(bookId);
        Introduction introduction = book.getIntroduction();
        introduction.setBookId(bookId);
        coverRepository.save(cover);
        introductionRepository.save(introduction);
    }

    private void completeBook(Book book) {
        if (book == null)
            return;
        int bookId = book.getId();
        Cover cover = coverRepository.findById(bookId).orElse(null);
        Introduction introduction = introductionRepository.findById(bookId).orElse(null);
        book.setCover(cover);
        book.setIntroduction(introduction);
    }
}
