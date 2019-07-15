package com.example.xiaowu.controller;

import com.example.xiaowu.SwaggerTagConstants;
import com.example.xiaowu.domain.User;
import com.example.xiaowu.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "用户接口")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户分页列表" ,  notes="用户分页列表", tags = {SwaggerTagConstants.ADMIN})
    @RequestMapping(value="/pagelist",method= RequestMethod.GET)
    public PageInfo<User> pagelist(@RequestParam(name = "pageNum") int pageNum, @RequestParam(name = "pageSize")int pageSize){


        //开始使用mybatis 分页插件
        PageHelper.startPage(pageNum,pageSize);
        System.out.println("访问了分页列表");
        List<User> list = userService.findAllUser();
        PageInfo<User> pageInfo = new PageInfo<User>(list);

        return pageInfo;
    }


    @ApiOperation(value = "用户列表" ,  notes="用户列表" ,tags = {SwaggerTagConstants.ADMIN})
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public List<User> list(){
        System.out.println("访问了不是分页的接口数据");
        List<User> list = userService.findAllUser();
        return list;
    }

}
