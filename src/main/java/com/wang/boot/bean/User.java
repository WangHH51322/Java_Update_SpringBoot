package com.wang.boot.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/12/30 15:19
 */
@Data
@NoArgsConstructor
@ToString
public class User {
    private int age;
    private String name;

    private Pet pet;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public User(int age, String name, Pet pet) {
        this.age = age;
        this.name = name;
        this.pet = pet;
    }
}
