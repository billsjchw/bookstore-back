package com.example.bookstore.service.impl;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.dao.OrderDao;
import com.example.bookstore.entity.Authority;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.User;
import com.example.bookstore.misc.BookstoreUserDetails;
import com.example.bookstore.service.BookService;
import com.example.bookstore.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {
    @Autowired private BookDao bookDao;
    @Autowired private OrderDao orderDao;

    @Override
    public Message findBookById(int id) {
        Book book = bookDao.findById(id);
        if (book == null)
            return new Message("BOOK_NOT_FOUND", null);
        return new Message("SUCCESS", book);
    }

    @Override
    public Message findAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Order.asc("id")));
        Page<Book> books = bookDao.findAll(pageable);
        return new Message("SUCCESS", books);
    }

    @Override
    public Message BookFuzzySearch(String keyword, int page, int size) {
        Example<Book> example = Example.of(
                new Book(keyword, keyword, keyword),
                ExampleMatcher
                        .matchingAny()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        );
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Order.asc("id")));
        Page<Book> books = bookDao.findAll(example, pageable);
        return new Message("SUCCESS", books);
    }

    @Override
    public Message addBook(Book book) {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        if (!user.getAuthorities().contains(new Authority(Authority.AuthorityId.BOOK_ADMIN)))
            return new Message("REJECTED", null);
        book = bookDao.saveAndFlush(book);
        return new Message("SUCCESS", book);
    }

    @Override
    public Message editBook(Book book) {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        if (!user.getAuthorities().contains(new Authority(Authority.AuthorityId.BOOK_ADMIN)))
            return new Message("REJECTED", null);
        if (!bookDao.existsById(book.getId()))
            return new Message("BOOK_NOT_FOUND", null);
        bookDao.saveAndFlush(book);
        return new Message("SUCCESS", null);
    }

    @Override
    public Message deleteBookById(int id) {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        if (!user.getAuthorities().contains(new Authority(Authority.AuthorityId.BOOK_ADMIN)))
            return new Message("REJECTED", null);
        if (orderDao.bookIsOrdered(id))
            return new Message("BOOK_IS_ORDERED", null);
        bookDao.deleteById(id);
        return new Message("SUCCESS", null);
    }
}
