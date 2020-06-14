package com.example.bookstore.service.impl;

import com.example.bookstore.entity.User;
import com.example.bookstore.service.LoginService;
import com.example.bookstore.util.Message;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional(rollbackFor = Exception.class)
public class LoginServiceImpl implements LoginService {
    @Override
    public Message login() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return new Message("SUCCESS", user);
    }
}
