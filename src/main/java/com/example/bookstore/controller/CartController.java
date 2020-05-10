package com.example.bookstore.controller;

import com.example.bookstore.service.CartService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired private CartService cartService;

    @GetMapping("/cart/find-my-cart")
    Message findMyCart() {
        return cartService.findMyCart();
    }

    @PostMapping("/cart/add-to-cart")
    Message addToCart(@RequestParam String isbn, @RequestParam int increment) {
        return cartService.addToCart(isbn, increment);
    }
}
