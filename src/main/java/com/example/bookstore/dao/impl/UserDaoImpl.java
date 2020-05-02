package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.Avatar;
import com.example.bookstore.entity.User;
import com.example.bookstore.repository.AvatarRepository;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired UserRepository userRepo;
    @Autowired AvatarRepository avatarRepo;

    @Override
    public User getByUsername(String username) {
        User user = userRepo.findById(username).orElse(null);
        Avatar avatar = avatarRepo.findById(username).orElse(null);
        if (user == null || avatar == null)
            return null;
        user.setAvatar(avatar.getData());
        return user;
    }
}
