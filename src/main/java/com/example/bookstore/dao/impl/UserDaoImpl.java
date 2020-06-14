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
    private @Autowired UserRepository userRepo;
    private @Autowired AvatarRepository avatarRepo;

    @Override
    public User findOneByUsername(String username) {
        User user = userRepo.findOneByUsername(username).orElse(null);
        completeUser(user);
        return user;
    }

    private void completeUser(User user) {
        if (user == null)
            return;
        int userId = user.getId();
        Avatar avatar = avatarRepo.findById(userId).orElse(null);
        user.setAvatar(avatar);
    }
}
