package com.wang.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/12/29 22:02
 */

/**
 * 主程序类
 * 使用@SpringBootApplication 表明这是一个SpringBoot应用
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class,args);
    }
}
