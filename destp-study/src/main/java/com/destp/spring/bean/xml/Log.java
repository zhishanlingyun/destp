package com.destp.spring.bean.xml;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String key();

    String value() default "";

}
