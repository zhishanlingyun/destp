package com.destp.jodd.vtor;

import com.destp.bean.User;
import com.google.common.collect.Collections2;
import jodd.vtor.Check;
import jodd.vtor.ValidationContext;
import jodd.vtor.Violation;
import jodd.vtor.Vtor;
import jodd.vtor.constraint.MaxLengthConstraint;
import jodd.vtor.constraint.NotNullConstraint;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by zsly on 17-5-3.
 */
public class Demo {

    public void demo1(){
        User user = new User(123123L,"zhangsan",10);
        Vtor vtor = new Vtor();
        ValidationContext context = new ValidationContext();
        context.add(new Check("name",new NotNullConstraint()));
        context.add(new Check("name",new MaxLengthConstraint(3)));
        vtor.validate(context,user);
        List<Violation> result = vtor.getViolations();
        if(!CollectionUtils.isEmpty(result)){
            for(Violation violation : result){
                System.out.println(violation.getName()+"-"+violation.getConstraint());
            }
        }else {
            System.out.println("ok");
        }
    }

    public void demo2(){
        User user = new User(123123L,"",10);
        Vtor vtor = new Vtor();
        vtor.validate(user);
        System.out.println(vtor.hasViolations());
        List<Violation> result = vtor.getViolations();
        if(!CollectionUtils.isEmpty(result)){
            for(Violation violation : result){
                System.out.println(violation.getName()+"-"+violation.getCheck().getMessage());
            }
        }else {
            System.out.println("ok");
        }
    }

    public static void main(String[] args) {
        new Demo().demo2();
    }


}
