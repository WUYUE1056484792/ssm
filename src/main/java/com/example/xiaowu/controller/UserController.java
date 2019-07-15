package com.example.xiaowu.controller;

import com.example.xiaowu.domain.User;
import com.example.xiaowu.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("pagelist")
    public PageInfo<User> pagelist(){

        Integer pageNum = 1;
        Integer pageSize = 10;

        //开始使用mybatis 分页插件
        PageHelper.startPage(pageNum,pageSize);
        System.out.println("访问了分页列表");
        List<User> list = userService.findAllUser();
        PageInfo<User> pageInfo = new PageInfo<User>(list);

        return pageInfo;

        /*System.out.println("访问了耶！！！！！！！！！！！");
        List<User> list = userService.findAllUser();
        return list;*/
    }


    @RequestMapping("list")
    public List<User> list(){
        System.out.println("访问了不是分页的接口数据");
        List<User> list = userService.findAllUser();
        return list;
    }

}
