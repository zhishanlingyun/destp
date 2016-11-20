package com.destp.sharend.shop.biz.home.web;

import com.destp.common.ui.web.view.RequestResult;
import com.destp.dto.User;
import com.destp.common.ui.web.view.annotation.JsonPage;
import com.destp.sharend.shop.frameworker.view.Result;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/11/18 0018.
 */
@Controller
@RequestMapping("/reg")
public class RegisterController {

    @ResponseBody
    @RequestMapping("/sendmail")
    @JsonPage(page = "/1/popup/t.vm")
    public RequestResult toMailCheck(@Validated User user, BindingResult br /*String id,String mail,String username*/){
        //System.out.println("@@@@@@@@@@@@@ "+bindingResult);
        if(br.hasErrors()){

        }
        System.out.println("@@@@@@@@@@ "+user);
        String html = "<div><p>这是后台返回来的html片段</p></div>";
        RequestResult result = new RequestResult();
        result.setState(true);
        //result.setContent(html);
        return result;
    }

    @ResponseBody
    @RequestMapping("/register")
    public Result register(){
        Result result = new Result();
        result.setSucess(true);
        return result;
    }


}
