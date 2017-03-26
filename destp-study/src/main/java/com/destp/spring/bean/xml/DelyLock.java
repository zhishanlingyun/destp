package com.destp.spring.bean.xml;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DelyLock {

    String key();

    String expire();

}
