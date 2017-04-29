package com.destp.web.controller.scarebuy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
@Controller
@RequestMapping("/scarebuy/jd")
public class JdScarebuyController {

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/scarebuy/jdscarebuy");
        mv.addObject("username","张三");
        return mv;
    }

}
