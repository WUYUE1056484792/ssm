package com.example.xiaowu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.example")
public class XiaowuApplication {

	public static void main(String[] args) {
		SpringApplication.run(XiaowuApplication.class, args);
	}

}
