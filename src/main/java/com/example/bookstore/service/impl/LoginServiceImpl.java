package com.example.bookstore.service.impl;

import com.example.bookstore.entity.User;
import com.example.bookstore.misc.BookstoreUserDetails;
import com.example.bookstore.service.LoginService;
import com.example.bookstore.dto.Message;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {
    @Override
    public Message login() {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        return new Message("SUCCESS", user);
    }
}
