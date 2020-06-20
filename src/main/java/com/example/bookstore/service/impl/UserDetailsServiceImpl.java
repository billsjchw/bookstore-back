package com.example.bookstore.service.impl;

import com.example.bookstore.dao.UserDao;
import com.example.bookstore.entity.User;
import com.example.bookstore.misc.BookstoreUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional(rollbackFor = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findOneByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("");
        return new BookstoreUserDetails(user);
    }
}
