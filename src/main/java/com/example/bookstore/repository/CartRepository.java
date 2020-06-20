package com.example.bookstore.repository;

import com.example.bookstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Optional<Cart> findOneByUserId(Integer userId);
}
