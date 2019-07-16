package com.example.xiaowu.controller;

import com.example.xiaowu.SwaggerTagConstants;
import com.example.xiaowu.domain.User;
import com.example.xiaowu.service.UserService;
import com.example.xiaowu.utils.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "用户接口")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

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

    @ApiOperation(value = "测试redis" ,  notes="测试redis" ,tags = {SwaggerTagConstants.ADMIN})
    @RequestMapping(value="/redis",method= RequestMethod.GET)
    public String redis(@RequestParam(name = "key") String key,@RequestParam(name = "value") String value){
        System.out.println("测试redis存数据");
        //redisTemplate.opsForValue().set(key, value);
        redisUtil.set(key,value);
        return key;
    }

}
