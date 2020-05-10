package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Cover;
import com.example.bookstore.entity.Introduction;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CoverRepository;
import com.example.bookstore.repository.IntroductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {
    @Autowired private BookRepository bookRepository;
    @Autowired private CoverRepository coverRepository;
    @Autowired private IntroductionRepository introductionRepository;

    @Override
    public boolean existByIsbn(String isbn) {
        return bookRepository.existsById(isbn);
    }

    @Override
    public Book findByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElse(null);
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
    public void save(Book book) {
        Cover cover = book.getCover();
        Introduction introduction = book.getIntroduction();
        bookRepository.save(book);
        coverRepository.save(cover);
        introductionRepository.save(introduction);
    }

    private void completeBook(Book book) {
        if (book == null)
            return;
        String isbn = book.getIsbn();
        Cover cover = coverRepository.findById(isbn).orElse(null);
        Introduction introduction = introductionRepository.findById(isbn).orElse(null);
        book.setCover(cover);
        book.setIntroduction(introduction);
    }
}
