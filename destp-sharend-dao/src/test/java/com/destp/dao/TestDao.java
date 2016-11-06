package com.destp.dao;

import com.destp.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.support.incrementer.SybaseAnywhereMaxValueIncrementer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuli10 on 2016/10/23.
 */
public class TestDao {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-jdbc-datasource.xml");
        UserDao dao = (UserDao) context.getBean("userDao");
        List<User> users = new ArrayList<User>();
        for(int i=0;i<2;i++){
            User user = new User();
            user.setUsername("zhangsan"+i);
            user.setPassword("123");
            user.setPhone("292803929"+i);
            user.setEmail(i+"xxx@aaa.com");
            users.add(user);
        }

        //dao.create("user.createUser",user);
        dao.batchInsert(users,"user.createUser");
        /*List<User> userList = dao.queryList("user.queryUserAll",null);
        System.out.println(userList.get(0));*/
    }

}
