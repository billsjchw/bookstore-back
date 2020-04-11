package com.example.bookstore.controller;

import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private @Autowired UserService userService;

    @GetMapping(value = "/user/find-all")
    List<User> findAll() {
        return userService.findAll();
    }

    @PutMapping(value = "/user/register")
    UserService.Status register(@RequestBody User user) {
        return userService.register(user);
    }

    @PutMapping(value = "/user/ban")
    UserService.Status ban(@RequestParam(name = "username") String username) {
        return userService.ban(username);
    }

    @PutMapping(value = "/user/free")
    UserService.Status free(@RequestParam(name = "username") String username) {
        return userService.free(username);
    }
}
