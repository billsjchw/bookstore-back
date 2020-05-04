package com.example.bookstore.repository;

import com.example.bookstore.entity.Introduction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IntroductionRepository extends MongoRepository<Introduction, String> {
    List<Introduction> findAllByOrderByIsbn();
}
