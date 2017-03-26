package com.destp.spring.bean.aop;

import com.destp.spring.bean.xml.DelyLock;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
@Aspect
@Repository
public class DelyLockAspect {

    @Before("@annotation(lock)")
    public void lockDely(JoinPoint jp, DelyLock lock){
        System.out.println(Arrays.toString(jp.getArgs()));
        String expresskey = lock.key();
        String expressexpirt = lock.expire();
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("lock",jp.getArgs()[0]);
        String key = parser.parseExpression(expresskey).getValue(context,String.class);
        int expirt = parser.parseExpression(expressexpirt).getValue(context,int.class);
        System.out.println("key = "+key+" expirt = "+expirt);
        System.out.println("---------------------- Before search --------------------");

    }

}
