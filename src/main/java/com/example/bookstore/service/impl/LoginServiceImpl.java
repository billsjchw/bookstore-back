package com.example.bookstore.service.impl;

import com.example.bookstore.misc.BookstoreAuthority;
import com.example.bookstore.service.LoginService;
import com.example.bookstore.util.Message;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {
    @Override
    public Message login() {
        boolean admin = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().contains(new BookstoreAuthority(true));
        return new Message("SUCCESS", admin);
    }
}
