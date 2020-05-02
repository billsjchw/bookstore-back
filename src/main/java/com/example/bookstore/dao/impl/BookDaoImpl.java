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
    public void save(Book book) {
        Cover cover = new Cover(book);
        Introduction intro = new Introduction(book);
        bookRepo.save(book);
        coverRepo.save(cover);
        introRepo.save(intro);
    }
}
