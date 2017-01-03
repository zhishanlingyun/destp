package com.destp.sharend.shop.biz.home.web;

import com.alibaba.fastjson.JSONArray;
import com.destp.sharend.shop.biz.home.dto.ChartDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @RequestMapping(value="/data",method=RequestMethod.GET)
    @ResponseBody
    public String getData(){
        String[] xais = {"自营促销主","pop0促销主","pop1促销主",
                "pop2促销主","pop3促销主","pop4促销主","pop5促销主",
                "自营促销子","pop0促销子","pop1促销子","pop2促销子",
                "pop3促销子","pop4促销子","pop5促销子","mqtask0",
                "mqtask1","mqtask2","mqtasksum0","mqtasksum1","mqtasksum2"};
        //ChartDto[] chartDtos = new ChartDto[xais.length];
        List<ChartDto> chartDtos = new ArrayList<ChartDto>();
        for(int i=0;i<xais.length;i++){
            ChartDto chartDto = new ChartDto();
            chartDto.setName(xais[i]);
            //chartDto.setY(getRodam(0,10));
            chartDto.setY(0);
            chartDtos.add(chartDto);
        }
        String json = JSONArray.toJSONString(chartDtos);
        //return "m("+json+")";
        return json;
    }

    private int getRodam(int min,int max){
        Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
    }

}
