package com.wang.boot.controller;

import com.wang.boot.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/12/29 22:04
 */

//@ResponseBody
//@Controller

@RestController
public class HelloController {

    @Autowired  //自动注入容器中的Car实例
    Car car;

    @RequestMapping("/car")
    public Car getCar(){
        return car;
    }

    @RequestMapping("/hello")
    public String handle01(){
        return "Hello, SpringBoot2!" + "你好!";
    }
}
