package com.destp.sharend.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2017/3/2 0002.
 */
@Controller
@RequestMapping("/home")
public class HomePageController {

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

}
