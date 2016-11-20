package com.destp.sharend.shop.frameworker.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class SimpleValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return false;
    }

    public void validate(Object target, Errors errors) {

    }
}
