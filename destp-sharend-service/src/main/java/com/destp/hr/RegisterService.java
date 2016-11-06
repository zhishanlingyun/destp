package com.destp.hr;

import com.destp.dao.RegisterUserDao;
import com.destp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
@Service
public class RegisterService {

    @Autowired
    private RegisterUserDao registerUserDao;

    public boolean registerUser(User user){
        return registerUserDao.registerUser(user);
    }

}
