package com.example.bookstore.controller;

import com.example.bookstore.service.LoginService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired private LoginService loginService;

    @GetMapping("/login")
    Message login() {
        return loginService.login();
    }
}
