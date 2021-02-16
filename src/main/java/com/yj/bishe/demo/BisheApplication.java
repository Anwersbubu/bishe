package com.yj.bishe.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@MapperScan(basePackages = "com.yj.bishe.demo.dao")
@ComponentScan(basePackages = "com.yj.bishe")
public class BisheApplication {

    public static void main(String[] args) {
        SpringApplication.run(BisheApplication.class, args);
    }

}
