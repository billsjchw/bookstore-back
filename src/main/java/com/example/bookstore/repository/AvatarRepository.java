package com.example.bookstore.repository;

import com.example.bookstore.entity.Avatar;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AvatarRepository extends MongoRepository<Avatar, Integer> {}
