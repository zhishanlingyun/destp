package com.destp.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public class IocContent {

    private static ApplicationContext applicationContext = null;

    static {
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
    }

    public static  <T> T getBean(T t){
        return (T) applicationContext.getBean(t.getClass());
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public static <T> Class<T> getBean(Class<T> clazz){
        return (Class<T>) applicationContext.getBean(clazz);
    }



}
