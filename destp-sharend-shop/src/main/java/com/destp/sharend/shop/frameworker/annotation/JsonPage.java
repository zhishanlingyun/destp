package com.destp.sharend.shop.frameworker.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonPage {

    String page() default "";

}
