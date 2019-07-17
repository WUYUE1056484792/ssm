package com.example.xiaowu.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.xiaowu.SwaggerTagConstants;
import com.example.xiaowu.domain.DataMsg;
import com.example.xiaowu.domain.Radar;
import com.example.xiaowu.domain.User;
import com.example.xiaowu.service.UserService;
import com.example.xiaowu.utils.DateUtil;
import com.example.xiaowu.utils.MUSICUtil;
import com.example.xiaowu.utils.RedisUtil;
import com.example.xiaowu.utils.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @ApiOperation(value = "插入雷达图" ,  notes="插入雷达图" ,tags = {SwaggerTagConstants.ADMIN})
    @RequestMapping(value="/insertRadarUrl",method= RequestMethod.POST)
    public DataMsg insertRadarUrl(@RequestBody Radar radar){
        System.out.println("==========插入雷达图============");
        DataMsg dataMsg = new DataMsg();
        radar.setId(UuidUtil.get32UUID());
        //根据插入时间查询是否有数据
        Radar radar1 = userService.findUrlByInsertTime(radar);
        if(radar1!=null){
            dataMsg.setStateCode("400");
            dataMsg.setStateMsg("数据库存在");
        }else {
            userService.insertRadarUrl(radar);
        }

        return dataMsg;
    }




    @ApiOperation(value = "测试jar" ,  notes="测试jar" ,tags = {SwaggerTagConstants.ADMIN})
    @RequestMapping(value="/testjar",method= RequestMethod.GET)
    public String testjar(){
        System.out.println("测试jar");

        String interfaceId = "getSurfEleInRectByTime" ;
        HashMap<String, String> params = new HashMap<String, String>();
        //必选参数
        params.put("dataCode", "SURF_CHN_MUL_HOR") ; //资料代码
        params.put("elements", "Lat,Lon,PRE_1h,PRE_3h,PRE_6h,PRE_12h,PRE_24h") ;
        String date= DateUtil.getMusicDate(0);
        params.put("times", date); //系统当前时间
        params.put("minLat", "3.51");
        params.put("minLon", "73.33");
        params.put("maxLat", "53.33");
        params.put("maxLon", "135.05");
        params.put("limitCnt", "30");
        String dataFormat = "json";
        JSONObject jsonObject= MUSICUtil.getData(interfaceId, params, dataFormat);



        return "";
    }



}
