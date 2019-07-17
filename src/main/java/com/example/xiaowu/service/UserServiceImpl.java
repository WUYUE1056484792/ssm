package com.example.xiaowu.service;


import com.example.xiaowu.dao.UserMapper;
import com.example.xiaowu.domain.Radar;
import com.example.xiaowu.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> findAllUser() {
        List<User> list = userMapper.findAllUser();
        return list;
    }

    @Override
    public void insertRadarUrl(Radar radar) {
        userMapper.insertRadarUrl(radar);
    }

    @Override
    public Radar findUrlByInsertTime(Radar radar) {
        Radar radar1 = userMapper.findUrlByInsertTime(radar);
        return radar1;
    }
}
