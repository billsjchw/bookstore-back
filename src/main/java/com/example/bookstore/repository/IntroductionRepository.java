package com.example.bookstore.repository;

import com.example.bookstore.entity.Introduction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IntroductionRepository extends MongoRepository<Introduction, String> {}
