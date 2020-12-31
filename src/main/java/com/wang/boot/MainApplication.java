package com.wang.boot;

import ch.qos.logback.core.db.DBHelper;
import com.wang.boot.bean.Pet;
import com.wang.boot.bean.User;
import com.wang.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/12/29 22:02
 */

/**
 * 主程序类
 * 使用@SpringBootApplication 表明这是一个SpringBoot应用
 *
 * @SpringBootApplication 等同于
 *      @SpringBootConfiguration
 *      @EnableAutoConfiguration
 *      @ComponentScan("com.wang.boot") 这三个配置
 */
//@SpringBootApplication
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan("com.wang.boot")
public class MainApplication {

    public static void main(String[] args) {
        //1.返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //2.查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
//            System.out.println(name);
        }

        //3从容器中获取组件
        Pet pet01 = run.getBean("pet02", Pet.class);
        Pet pet02 = run.getBean("pet02", Pet.class);
        System.out.println(pet01);
        System.out.println("组件:" + (pet01 == pet02));

        //配置类本身也是组件,也即:代理对象
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);

        //如果@Configuration(proxyBeanMethods = true),则是代理对象调用方法. SpringBoot总是会检查这个组件是否在容器中注册,保持组件单实例
        //如果@Configuration(proxyBeanMethods = false),则会跳过检查这一步,直接创建新的实例
        User user01 = bean.user01();
        User user02 = bean.user01();
        System.out.println("User:" + (user01 == user02));

        User user = run.getBean("user01", User.class);
        Pet pet = run.getBean("pet02", Pet.class);
        System.out.println(user);
        //如果@Configuration(proxyBeanMethods = true),则user01这个组件,依赖了pet02组件:user01内的Pet和pet02是同一个实例
        System.out.println("Pet:" + (user.getPet() == pet));

        //5 获取组件 getBeanNamesForType(User.class):根据类名获取容器内的全部实例
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        System.out.println("============================================");
        for (String s : beanNamesForType) {
            System.out.println(s);
        }

        DBHelper dbHelper = run.getBean(DBHelper.class);
        System.out.println(dbHelper);
        System.out.println("============================================");

        //containsBean("xxx")   判断容器中是否包含组件 xxx
        boolean pet1 = run.containsBean("pet01");
        System.out.println("容器中包含pet01组件:" + pet1);
        boolean pet2 = run.containsBean("pet02");
        System.out.println("容器中包含pet02组件:" + pet2);
        boolean user1 = run.containsBean("user01");
        System.out.println("容器中包含user01组件:" + user1);
        boolean user2 = run.containsBean("user02");
        System.out.println("容器中包含user02组件:" + user2);

        boolean user11 = run.containsBean("user11");
        System.out.println("容器中包含beans.xml内的user11组件:" + user11);
        boolean pet11 = run.containsBean("pet11");
        System.out.println("容器中包含beans.xml内的pet11组件:" + pet11);
    }
}
