package com.example.bookstore.service.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.misc.BookstoreAuthority;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {
    @Autowired BookDao bookDao;

    @Override
    public Message findAllBooks() {
        List<Book> books = bookDao.findAll();
        return new Message("SUCCESS", books);
    }

    @Override
    public Message getBookByIsbn(String isbn) {
        Book book = bookDao.getByIsbn(isbn);
        if (book == null)
            return new Message("BOOK_NOT_FOUND", null);
        return new Message("SUCCESS", book);
    }

    @Override
    public Message addBook(Book book) {
        boolean admin = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().contains(new BookstoreAuthority(true));
        if (!admin)
            return new Message("NONPRIVILEGED", null);
        if (bookDao.existsByIsbn(book.getIsbn()))
            return new Message("DUP_ISBN", null);
        bookDao.save(book);
        return new Message("SUCCESS", null);
    }
}
