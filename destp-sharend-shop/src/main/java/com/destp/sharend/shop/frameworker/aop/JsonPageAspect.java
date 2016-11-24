package com.destp.sharend.shop.frameworker.aop;

import com.destp.common.ui.web.util.WebUtils;
import com.destp.common.ui.web.view.RequestResult;
import com.destp.sharend.shop.frameworker.annotation.JsonPage;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
@Aspect
@Component
public class JsonPageAspect implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Around("@annotation(com.destp.sharend.shop.frameworker.annotation.JsonPage)")
    public Object addJsonResult(ProceedingJoinPoint pjp){
        Object result = null;
        try {
            result = pjp.proceed();
            if(null!=result){
                if(result instanceof RequestResult){
                    RequestResult reqresult = (RequestResult)result;
                    System.out.println(pjp.getSignature().getName());
                    Signature signature = pjp.getSignature();
                    MethodSignature methodSignature = (MethodSignature)signature;
                    Method targetMethod = methodSignature.getMethod();
                    JsonPage jsonPage = targetMethod.getAnnotation(JsonPage.class);
                    if(null!=jsonPage){
                        String url = StringUtils.isNotEmpty(reqresult.getViewName())?reqresult.getViewName():(StringUtils.isNotEmpty(jsonPage.page())?jsonPage.page():null);
                        Assert.notNull(url,"弹出窗视图为空");
                        System.out.println(url);
                        render(url,reqresult,null,null);
                    }
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    protected void render(String viewName, RequestResult requestResult, HttpServletRequest request, HttpServletResponse response) throws Exception {
        VelocityConfigurer cfg = applicationContext.getBean(VelocityConfigurer.class);
        VelocityEngine engine = cfg.getVelocityEngine();
        VelocityContext context = new VelocityContext(WebUtils.mergePageResult(request,requestResult));
        StringWriter writer = new StringWriter();
        engine.getTemplate(viewName).merge(context, writer);
        String page = writer.toString();
        requestResult.setPagehtml(page);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
