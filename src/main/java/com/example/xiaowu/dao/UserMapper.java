package com.example.xiaowu.dao;

import com.example.xiaowu.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper     //声明是一个Mapper,与springbootApplication中的@MapperScan二选一写上即可
public interface UserMapper {
    public List<User> findAllUser();
}
