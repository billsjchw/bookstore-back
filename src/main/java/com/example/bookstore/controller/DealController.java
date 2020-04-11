package com.example.bookstore.controller;

import com.example.bookstore.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DealController {
    private @Autowired DealService dealService;

    @PutMapping("/deal/place-order")
    DealService.Status placeOrder(@RequestParam(name = "username") String username) {
        return dealService.placeOrder(username);
    }

    @PostMapping("/deal/add-to-cart")
    DealService.Status addToCart(
        @RequestParam(name = "username") String username,
        @RequestParam(name = "isbn") String isbn,
        @RequestParam(name = "num") int num
    ) {
        return dealService.addToCart(username, isbn, num);
    }
}
