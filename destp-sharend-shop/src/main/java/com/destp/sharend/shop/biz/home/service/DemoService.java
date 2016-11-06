package com.destp.sharend.shop.biz.home.service;

import com.destp.dao.UserDao;
import com.destp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/10/30 0030.
 */
@Service("demoService")
public class DemoService {

    @Resource
    private UserDao<User> userDao;

    public void getUsers(){
        List<User> users = userDao.queryList("user.queryUserAll",null);
        System.out.println(users.size());
    }

}
