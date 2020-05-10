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
    private @Autowired UserRepository userRepository;
    private @Autowired AvatarRepository avatarRepository;

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findById(username).orElse(null);
        completeUser(user);
        return user;
    }

    private void completeUser(User user) {
        if (user == null)
            return;
        String username = user.getUsername();
        Avatar avatar = avatarRepository.findById(username).orElse(null);
        user.setAvatar(avatar);
    }
}
