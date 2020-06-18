package com.example.bookstore.controller;

import com.example.bookstore.entity.CartItem;
import com.example.bookstore.service.CartService;
import com.example.bookstore.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    @Autowired CartService cartService;

    @GetMapping(value = "/cart/add-book-to-my-cart")
    Message addBookToMyCart(@RequestParam(value = "book-id") int bookId) {
        return cartService.addBookToMyCart(bookId);
    }

    @GetMapping(value = "/cart/find-item-in-my-cart")
    Message findItemInMyCart(@RequestParam(value = "book-id") int bookId) {
        return cartService.findItemInMyCart(bookId);
    }

    @GetMapping(value = "/cart/find-my-cart")
    Message findMyCart() {
        return cartService.findMyCart();
    }

    @PostMapping(value = "/cart/edit-item-in-my-cart")
    Message editItemInMyCart(@RequestBody CartItem cartItem) {
        return cartService.editItemInMyCart(cartItem);
    }

    @GetMapping(value = "/cart/remove-item-from-my-cart")
    Message removeItemFromMyCart(@RequestParam(value = "book-id") int bookId) {
        return cartService.removeItemFromMyCart(bookId);
    }
}
