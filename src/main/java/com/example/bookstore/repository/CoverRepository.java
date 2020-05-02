package com.example.bookstore.repository;

import com.example.bookstore.entity.Cover;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CoverRepository extends MongoRepository<Cover, String> {
    List<Cover> findAllByOrderByIsbn();
}
