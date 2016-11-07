package com.destp.sharend.shop.biz.home.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/11/6 0006.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("1/index");
        return mv;
    }

}
