package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.Avatar;
import com.example.bookstore.entity.User;
import com.example.bookstore.repository.AvatarRepository;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    private @Autowired UserRepository userRepo;
    private @Autowired AvatarRepository avatarRepo;

    @Override
    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    @Override
    public User findById(int id) {
        User user = userRepo.findById(id).orElse(null);
        completeUser(user);
        return user;
    }

    @Override
    public User findOneByUsername(String username) {
        User user = userRepo.findOneByUsername(username).orElse(null);
        completeUser(user);
        return user;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        Page<User> users = userRepo.findAll(pageable);
        for (User user : users)
            completeUser(user);
        return users;
    }

    @Override
    public Page<User> findAll(Example<User> example, Pageable pageable) {
        Page<User> users = userRepo.findAll(example, pageable);
        for (User user : users)
            completeUser(user);
        return users;
    }

    @Override
    public User saveAndFlush(User user) {
        Avatar avatar = user.getAvatar();
        user = userRepo.saveAndFlush(user);
        int userId = user.getId();
        avatar.setUserId(userId);
        avatarRepo.save(avatar);
        user.setAvatar(avatar);
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
