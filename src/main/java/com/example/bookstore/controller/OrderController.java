package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderItemsInMyCartDto;
import com.example.bookstore.service.OrderService;
import com.example.bookstore.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired private OrderService orderService;

    @PostMapping(value = "/order/order-items-in-my-cart")
    Message orderItemsInMyCart(@RequestBody OrderItemsInMyCartDto requestBody) {
        return orderService.orderItemsInMyCart(requestBody.getConsignee(), requestBody.getPaymentMethod());
    }

    @GetMapping(value = "/order/order-search")
    Message orderSearch(@RequestParam(value = "time-placed-start") String timePlacedStart,
                        @RequestParam(value = "time-placed-end") String timePlacedEnd,
                        @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        return orderService.orderSearch(timePlacedStart, timePlacedEnd, page, size);
    }

    @GetMapping(value = "/order/order-fuzzy-search")
    Message orderFuzzySearch(@RequestParam(value = "keyword") String keyword,
                             @RequestParam(value = "time-placed-start") String timePlacedStart,
                             @RequestParam(value = "time-placed-end") String timePlacedEnd,
                             @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        return orderService.orderFuzzySearch(keyword, timePlacedStart, timePlacedEnd, page, size);
    }

    @GetMapping(value = "/order/my-order-search")
    Message myOrderSearch(@RequestParam(value = "time-placed-start") String timePlacedStart,
                          @RequestParam(value = "time-placed-end") String timePlacedEnd,
                          @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        return orderService.myOrderSearch(timePlacedStart, timePlacedEnd, page, size);
    }

    @GetMapping(value = "/order/my-order-fuzzy-search")
    Message myOrderFuzzySearch(@RequestParam(value = "keyword") String keyword,
                               @RequestParam(value = "time-placed-start") String timePlacedStart,
                               @RequestParam(value = "time-placed-end") String timePlacedEnd,
                               @RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
        return orderService.myOrderFuzzySearch(keyword, timePlacedStart, timePlacedEnd, page, size);
    }
}
