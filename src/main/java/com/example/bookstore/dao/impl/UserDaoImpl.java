package com.example.bookstore.dao.impl;

import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.*;
import com.example.bookstore.repository.AvatarRepository;
import com.example.bookstore.repository.CoverRepository;
import com.example.bookstore.repository.IntroductionRepository;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired UserRepository userRepo;
    @Autowired AvatarRepository avatarRepo;
    @Autowired CoverRepository coverRepo;
    @Autowired IntroductionRepository introRepo;

    @Override
    public User getByUsername(String username) {
        User user = userRepo.findById(username).orElse(null);
        Avatar avatar = avatarRepo.findById(username).orElse(null);
        if (user == null || avatar == null)
            return null;
        user.complete(avatar);
        if (!completeOrder(user.getCart()))
            return null;
        for (Order order : user.getHistory())
            if (!completeOrder(order))
                return null;
        return user;
    }

    @Override
    public void save(User user) {
        Avatar avatar = new Avatar(user);
        userRepo.save(user);
        avatarRepo.save(avatar);
    }

    private boolean completeOrder(Order order) {
        for (Item item : order.getItems()) {
            Book book = item.getBook();
            String isbn = book.getIsbn();
            Cover cover = coverRepo.findById(isbn).orElse(null);
            Introduction intro = introRepo.findById(isbn).orElse(null);
            if (cover == null || intro == null)
                return false;
            book.complete(cover, intro);
        }
        return true;
    }
}
