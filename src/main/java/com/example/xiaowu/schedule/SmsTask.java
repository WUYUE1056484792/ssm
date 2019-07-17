package com.example.xiaowu.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.xiaowu.domain.Radar;
import com.example.xiaowu.service.UserService;
import com.example.xiaowu.utils.HttpRequestUtil;

import com.example.xiaowu.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SmsTask {

    @Autowired
    private UserService userService;

    @Scheduled(cron = "0 */10 * * * ?")
    public void work() {
        System.out.println("定时任务开始执行........");
        //每隔十分钟调雷达图的接口往数据库中插数据
        String result = HttpRequestUtil.interfaceUtil("http://zzqx.zhengzhou.gov.cn/WECHAT_WEATHER.getRadarImg.do", "", "", "");
        System.out.println("雷达图接口的返回值 " + result.toString());
        JSONObject res = JSON.parseObject(result);
        JSONArray data = res.getJSONArray("data");
        for(int i=0;i<data.size();i++) {
            JSONObject urlTime = data.getJSONObject(i);
            System.out.println("dataInsertTime " + urlTime.get("dataInsertTime").toString());
            System.out.println("httpUrl " + urlTime.get("httpUrl").toString());

            Radar radar = new Radar();
            radar.setId(UuidUtil.get32UUID());
            radar.setInsert_time(urlTime.get("dataInsertTime").toString());
            radar.setUrl(urlTime.get("httpUrl").toString());
            Radar radar1 = userService.findUrlByInsertTime(radar);
            if(radar1!=null){
                System.out.println("该时间的雷达图存在，不插入");
            }else {
                System.out.println("该时间的雷达图不存在，插入");
                userService.insertRadarUrl(radar);
            }

        }

        //

    }

}
