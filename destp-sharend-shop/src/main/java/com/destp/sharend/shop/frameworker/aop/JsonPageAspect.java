package com.destp.sharend.shop.frameworker.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
@Aspect
@Component
public class JsonPageAspect {

    @After("@annotation(com.destp.sharend.shop.frameworker.annotation.JsonPage)")
    public void addJsonResult(JoinPoint jp){
        System.out.println("=========== JsonPageAspect ==========");

    }

}
