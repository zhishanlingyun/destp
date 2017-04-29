package com.destp.spring.xml;


import com.destp.common.User;
import com.destp.spring.bean.scan.dto.FileDao;
import com.destp.spring.bean.scan.dto.Lock;
import com.destp.spring.bean.scan.dto.RedisLock;
import com.destp.spring.bean.scan.service.SearchService;
import com.destp.spring.bean.scan.service.impl.FileSearchService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/1/2 0002.
 */
public class StringTest {


    private static ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

    //注解空值是类名
    //bean可以通过注解的value获得
    public static void demo1(){
        SearchService searchService = (SearchService)context.getBean("fileservice");
        System.out.println(searchService.foo("mmmmmmmmmm"));
        System.out.println(context.getBeanDefinitionCount());
        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        System.out.println(context.getBeansOfType(SearchService.class));
        System.out.println(context.getBeansOfType(FileDao.class));
    }

    public static void demo2(){
        SearchService searchService = (SearchService)context.getBean("fileservice");
        System.out.println(searchService.foo("mmmmmmmmmmmm"));
        System.out.println("--------------------------------------");
        Lock lock = new RedisLock("J_1231231_U",129213);
        System.out.println(searchService.search(lock,"kkkkkkk"));
    }

    public static void demo6(){

        ConfigurableApplicationContext cac = (ConfigurableApplicationContext)context;
        System.out.println(cac.getEnvironment());
        /*ResourceLoader resourceLoader = new DefaultResourceLoader();
        System.out.println(resourceLoader.getClassLoader());
        System.out.println(resourceLoader.getResource("spring-config.xml").exists());*/
    }

   /* public static void demo3(){
        ConcurrentExecutor.submit(new ConcurrentHandler() {
            @Override
            public void handle(Object o) {

            }

            @Override
            public List getTask() {
                return null;
            }

            @Override
            public void exceptionHandle(Object o, Throwable throwable) {

            }
        },100,true,10, TimeUnit.SECONDS,true);
    }*/

    public static void main(String[] args) {
        //demo1();
        //demo2();
        demo6();



    }
}

