package com.example.bookstore.controller;

import com.example.bookstore.entity.CartItem;
import com.example.bookstore.service.CartService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    @Autowired CartService cartService;

    @GetMapping(value = "/add-book-to-my-cart")
    Message addBookToMyCart(@RequestParam(value = "book-id") int bookId) {
        return cartService.addBookToMyCart(bookId);
    }

    @GetMapping(value = "/find-item-in-my-cart")
    Message findItemInMyCart(@RequestParam(value = "book-id") int bookId) {
        return cartService.findItemInMyCart(bookId);
    }

    @GetMapping(value = "/find-my-cart")
    Message findMyCart() {
        return cartService.findMyCart();
    }

    @PostMapping(value = "/edit-item-in-my-cart")
    Message editItemInMyCart(@RequestBody CartItem cartItem) {
        return cartService.editItemInMyCart(cartItem);
    }

    @GetMapping(value = "/remove-item-from-my-cart")
    Message removeItemFromMyCart(@RequestParam(value = "book-id") int bookId) {
        return cartService.removeItemFromMyCart(bookId);
    }
}
