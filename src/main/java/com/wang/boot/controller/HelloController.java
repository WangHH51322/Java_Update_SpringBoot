package com.wang.boot.controller;

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

    @RequestMapping("/hello")
    public String handle01(){
        return "Hello, SpringBoot2!";
    }
}
