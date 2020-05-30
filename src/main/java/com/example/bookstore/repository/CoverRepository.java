package com.example.bookstore.repository;

import com.example.bookstore.entity.Cover;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoverRepository extends MongoRepository<Cover, Integer> {}
