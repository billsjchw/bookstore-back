package com.example.bookstore.service.impl;

import com.example.bookstore.dao.StatDao;
import com.example.bookstore.dto.Message;
import com.example.bookstore.dto.PersonalStat;
import com.example.bookstore.dto.TopConsumer;
import com.example.bookstore.dto.TopSeller;
import com.example.bookstore.entity.Authority;
import com.example.bookstore.entity.User;
import com.example.bookstore.misc.BookstoreUserDetails;
import com.example.bookstore.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class StatServiceImpl implements StatService {
    @Autowired private StatDao statDao;

    @Override
    public Message findTopSellers(int n, String timePlacedStart, String timePlacedEnd) {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        if (!user.getAuthorities().contains(new Authority(Authority.AuthorityId.STAT)))
            return new Message("REJECTED", null);
        List<TopSeller> topSellers = statDao.findTopSellers(n, timePlacedStart, timePlacedEnd);
        return new Message("SUCCESS", topSellers);
    }

    @Override
    public Message findTopConsumers(int n, String timePlacedStart, String timePlacedEnd) {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        if (!user.getAuthorities().contains(new Authority(Authority.AuthorityId.STAT)))
            return new Message("REJECTED", null);
        List<TopConsumer> topSellers = statDao.findTopConsumers(n, timePlacedStart, timePlacedEnd);
        return new Message("SUCCESS", topSellers);
    }

    @Override
    public Message calcMyPersonalStat(String timePlacedStart, String timePlacedEnd) {
        User user = ((BookstoreUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        PersonalStat personalStat = statDao.calcMyPersonalStat(user.getId(), timePlacedStart, timePlacedEnd);
        return new Message("SUCCESS", personalStat);
    }
}
