package com.example.xiaowu.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SmsTask {
    @Scheduled(cron = "0/10 * * * * *")
    public void work() {
        System.out.println("定时任务开始执行........");
    }

}
