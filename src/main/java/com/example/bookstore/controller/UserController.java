package com.example.bookstore.controller;

import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import com.example.bookstore.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired private UserService userService;

    @PostMapping(value = "/user/register")
    Message register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping(value = "/user/find-all-users")
    Message findAllUsers(@RequestParam(value = "page") int page,
                         @RequestParam(value = "size") int size) {
        return userService.findAllUsers(page, size);
    }

    @GetMapping(value = "/user/user-fuzzy-search")
    Message userFuzzySearch(@RequestParam(value = "keyword") String keyword,
                            @RequestParam(value = "page") int page,
                            @RequestParam(value = "size") int size) {
        return userService.userFuzzySearch(keyword, page, size);
    }

    @GetMapping(value = "/user/set-user-enabled")
    Message setUserEnabled(@RequestParam(value = "user-id") int userId,
                           @RequestParam(value = "enabled") boolean enabled) {
        return userService.setUserEnabled(userId, enabled);
    }
}
