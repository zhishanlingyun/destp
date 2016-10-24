package com.destp.dao;

import com.destp.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.support.incrementer.SybaseAnywhereMaxValueIncrementer;

import java.util.List;

/**
 * Created by liuli10 on 2016/10/23.
 */
public class TestDao {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-jdbc-datasource.xml");
        UserDao dao = (UserDao) context.getBean("userdao");
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123");
        user.setPhone("19280392990");
        user.setEmail("xxx@aaa.com");
        dao.create("user.createUser",user);
        /*List<User> userList = dao.queryList("user.queryUserAll",null);
        System.out.println(userList.get(0));*/
    }

}
