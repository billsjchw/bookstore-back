package com.example.bookstore.service;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private @Autowired BookDao bookDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Book findByISBN(String isbn) {
        return bookDao.findByISBN(isbn);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    @Transactional
    public void deleteByISBN(String isbn) {
        bookDao.deleteByISBN(isbn);
    }
}
