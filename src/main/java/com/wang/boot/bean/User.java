package com.wang.boot.bean;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/12/30 15:19
 */
public class User {
    private int age;
    private String name;

    private Pet pet;

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public User(int age, String name, Pet pet) {
        this.age = age;
        this.name = name;
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", pet=" + pet +
                '}';
    }
}
