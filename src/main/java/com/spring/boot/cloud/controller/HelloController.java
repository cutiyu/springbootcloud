package com.spring.boot.cloud.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("hello")
public class HelloController {

    @RequestMapping("springbootCloudHtml")
    public String helloSpringBootCloudHtml(){

        System.out.println("hello spring boot and cloud");

        return "index";
    }


    @ResponseBody
    @RequestMapping("springbootCloudString")
    public String helloSpringBootCloud(){

        System.out.println("hello spring boot and cloud");

        return "indexString";
    }


    @ResponseBody
    @RequestMapping("springbootCloudJson")
    public User helloSpringBootCloudJson(){

        System.out.println("hello spring boot and cloud");

        User user = new User();
        user.setAge(11);
        user.setId(10);
        user.setName("tf");

        return user;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class User {
        private Integer id;
        private String name;
        private Integer age;
    }
}


