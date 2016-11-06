package com.destp.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
@Repository
public class RegisterUserDao<User> extends BaseDao {

    private final static Logger log = Logger.getLogger(RegisterUserDao.class);

    public boolean registerUser(User user){
        try {
            this.create("",user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
