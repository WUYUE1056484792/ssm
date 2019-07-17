package com.example.xiaowu.service;

import com.example.xiaowu.domain.Radar;
import com.example.xiaowu.domain.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUser();
    public void insertRadarUrl(Radar radar);
    public Radar findUrlByInsertTime(Radar radar);
}
