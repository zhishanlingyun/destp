package com.destp.spring.bean.aop;

import com.destp.spring.bean.xml.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
@Aspect
@Repository
public class LogAspect {

    @Before("@annotation(log)")
    public void log(JoinPoint jp,Log log){
        System.out.println(Arrays.toString(jp.getArgs()));
        System.out.println(jp.getKind());
        System.out.println(jp.getSignature());
        System.out.println(jp.getThis());
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        System.out.println(methodSignature.getMethod());
        System.out.println(Arrays.toString(methodSignature.getMethod().getDeclaredAnnotations()));
        System.out.println(Arrays.toString(methodSignature.getMethod().getClass().getAnnotations()));
        System.out.println("log key = "+log.key()+" log value = "+log.value());
        System.out.println(Arrays.toString(jp.getArgs()));
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        String express = log.key();
        for(Object o : jp.getArgs()){
            context.setVariable(express.substring(1),o);
        }
        String value = parser.parseExpression(express).getValue(context,String.class);
        System.out.println("value = "+value);
        System.out.println("--------------- LogAspect before ----------------");
    }

}
