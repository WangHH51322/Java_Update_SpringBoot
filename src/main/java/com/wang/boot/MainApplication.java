package com.wang.boot;

import com.wang.boot.bean.Pet;
import com.wang.boot.bean.User;
import com.wang.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
    }
}
