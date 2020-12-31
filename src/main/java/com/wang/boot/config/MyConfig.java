package com.wang.boot.config;

import ch.qos.logback.core.db.DBHelper;
import com.wang.boot.bean.Car;
import com.wang.boot.bean.Pet;
import com.wang.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Qing
 * @version 1.0
 * @date 2020/12/30 15:23
 */

/**
 * 1 配置类里面使用@Bean标注在方法上给容器注册组件,默认也是单实例的
 * 2 配置类本身也是组件
 * 3 proxyBeanMethods:代理bean的方法, 因此@Configuration有两种模式
 *      ①Full(proxyBeanMethods = true),全代理模式,保证每个@Bean方法被调用多少次返回的组件都是单实例的
 *      ②Lite(proxyBeanMethods = false),轻量模式,每个@Bean方法被调用多少次返回的组件都是新创建的
 *      组件依赖
 *
 * 4 @Import({User.class, DBHelper.class})
 *      给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名,后续可以直接调用
 *
 * 5 @ImportResource("classpath:beans.xml")导入Spring的配置文件(beans.xml),使其生效
 *
 * 6 @EnableConfigurationProperties(Car.class)
 *      ①开启Car这个实体类的配置绑定功能,将application.properties配置文件中 前缀为"mycar" 的信息与实体类中的属性值进行绑定
 *      ②把Car这个组件自动注册到容器中
 */

@Import({User.class, DBHelper.class})
@Configuration(proxyBeanMethods = true)  //告诉SpringBoot 这是一个配置类 == 配置文件
@ImportResource("classpath:beans.xml")
@EnableConfigurationProperties(Car.class)
public class MyConfig {

    /**
     * 外部无论对配置类中的这个组件注册方法调用多少次,获取的都是之前注册容器中的单实例对象
     * @return
     */

    @Bean   //给容器添加组件:  以方法名作为组件的id(user01)  返回值类型就是组件的类型(com.wang.boot.bean.User)
            //返回的值,就是组件在容器中的实例(new User(22,"小刘"))
    public User user01(){
        User user = new User(22, "小刘");
        //user01这个组件,依赖了pet01组件(前提是: proxyBeanMethods = true)
        user.setPet(pet01());
        return user;
    }

    @ConditionalOnBean(name = "user01")    //@ConditionalOnBean(name = "user01")表示当容器中有id为"user01"的组件时,才会将下面这个组件注入到容器中
    //否则使用containsBean("user02")方法,返回值是false
    @Bean
    public User user02(){
        User user = new User(20, "小小刘");
        //user01这个组件,依赖了pet01组件(前提是: proxyBeanMethods = true)
        user.setPet(pet01());
        return user;
    }

    @ConditionalOnMissingBean(name = "user")    //@ConditionalOnMissingBean(name = "user")表示当容器中没有id为"user"的组件时,才会将下面这个组件注入到容器中
    @Bean("pet02")    //也可以在@Bean()里面自定义组件的id
    public Pet pet01(){
        return new Pet("小黄");
    }
}
