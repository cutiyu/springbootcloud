package com.spring.boot.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScans(value = {@MapperScan(basePackages = {"com.spring.boot.cloud.dao.mapper"})})
public class SpringbootcloudApplication {

    public static void main(String[] args) {
        System.out.println("test1");
        SpringApplication.run(SpringbootcloudApplication.class, args);
    }

}
