package com.destp.sharend.shop.biz.home.web;

import com.destp.sharend.shop.frameworker.view.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/in")
    public Result login(){
        return null;
    }


    @RequestMapping("/out")
    public Result loginout(){
        return null;
    }

}
