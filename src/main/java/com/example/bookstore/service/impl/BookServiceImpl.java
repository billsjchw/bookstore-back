package com.example.bookstore.service.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Authority;
import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {
    @Autowired BookDao bookDao;

    @Override
    public Message findBookByIsbn(String isbn) {
        Book book = bookDao.findByIsbn(isbn);
        if (book == null)
            return new Message("BOOK_NOT_FOUND", null);
        return new Message("SUCCESS", book);
    }

    @Override
    public Message findAllBooks() {
        List<Book> books = bookDao.findAll();
        return new Message("SUCCESS", books);
    }

    @Override
    public Message addBook(Book book) {
        Collection<? extends GrantedAuthority> authorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        if (!authorities.contains(new Authority("BOOK_ADMIN")))
            return new Message("REJECTED", null);
        String isbn = book.getIsbn();
        if (bookDao.existByIsbn(isbn))
            return new Message("DUP_ISBN", null);
        bookDao.save(book);
        return new Message("SUCCESS", null);
    }
}
