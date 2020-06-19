package com.example.bookstore.dao;

import com.example.bookstore.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserDao {
    boolean existsByUsername(String username);
    User findById(int id);
    User findOneByUsername(String username);
    Page<User> findAll(Pageable pageable);
    Page<User> findAll(Example<User> example, Pageable pageable);
    User saveAndFlush(User user);
}
