package com.wang.boot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/12/31 13:44
 */

/**
 * 只有在容器中的组件( 使用@Component 注入容器 ), 才能拥有SpringBoot 提供的强大功能 ( 使用@ConfigurationProperties 注解)
 * @ConfigurationProperties(prefix = "mycar") 将application.properties配置文件中 前缀为"mycar" 的信息与下面的实体类中的属性值进行绑定
 *
 * 或者在配置文件 MyConfig中,使用@EnableConfigurationProperties(Car.class)注解,就不需要额外注入容器(@Component)
 * 因为Car这个实体类可能来自第三方包,所以没有@Component注解,此时就需要在本地的配置文件 MyConfig中使用@EnableConfigurationProperties注解
 */
//@Component
@ConfigurationProperties(prefix = "mycar")
public class Car {

    private String Band;
    private double price;

    public Car() {
    }

    public Car(String band, double price) {
        Band = band;
        this.price = price;
    }

    public String getBand() {
        return Band;
    }

    public void setBand(String band) {
        Band = band;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "Band='" + Band + '\'' +
                ", price=" + price +
                '}';
    }
}
