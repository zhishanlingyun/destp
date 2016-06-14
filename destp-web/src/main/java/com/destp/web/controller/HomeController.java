package com.destp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/6/9 0009.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("username","张三");
        return mv;
    }

}
