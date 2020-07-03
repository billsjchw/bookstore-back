package com.example.bookstore.controller;

import com.example.bookstore.dto.Message;
import com.example.bookstore.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatController {
    @Autowired private StatService statService;

    @GetMapping(value = "/stat/find-top-sellers")
    Message findTopSellers(@RequestParam(value = "n") int n,
                           @RequestParam(value = "time-placed-start") String timePlacedStart,
                           @RequestParam(value = "time-placed-end") String timePlacedEnd) {
        return statService.findTopSellers(n, timePlacedStart, timePlacedEnd);
    }

    @GetMapping(value = "/stat/find-top-consumers")
    Message findTopConsumers(@RequestParam(value = "n") int n,
                             @RequestParam(value = "time-placed-start") String timePlacedStart,
                             @RequestParam(value = "time-placed-end") String timePlacedEnd) {
        return statService.findTopConsumers(n, timePlacedStart, timePlacedEnd);
    }

    @GetMapping(value = "/stat/calc-my-personal-stat")
    Message calcMyPersonalStat(@RequestParam(value = "time-placed-start") String timePlacedStart,
                               @RequestParam(value = "time-placed-end") String timePlacedEnd) {
        return statService.calcMyPersonalStat(timePlacedStart, timePlacedEnd);
    }
}
