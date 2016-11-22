package com.destp.sharend.shop.frameworker.aop;

import com.destp.common.ui.web.view.RequestResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
@Aspect
@Component
public class JsonPageAspect {

    @Around("@annotation(com.destp.sharend.shop.frameworker.annotation.JsonPage)")
    //@after("execution(* com.destp.sharend.shop.biz.home.web.RegisterController.*(..))")
    public Object addJsonResult(ProceedingJoinPoint pjp){
        Object result = null;
        try {
            result = pjp.proceed();
            if(null!=result){
                if(result instanceof RequestResult){
                    ((RequestResult) result).setPagehtml("<div><p>这是后台返回来的html片段</p></div>");
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("=========== JsonPageAspect ==========");
        return result;
    }

}
