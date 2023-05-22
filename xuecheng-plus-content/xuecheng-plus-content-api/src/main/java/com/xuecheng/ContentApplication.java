package com.xuecheng;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 内容管理启动器
 */
@SpringBootApplication
public class ContentApplication {
   public static void main(String[] args) {
      SpringApplication.run(ContentApplication.class, args);
   }
}
