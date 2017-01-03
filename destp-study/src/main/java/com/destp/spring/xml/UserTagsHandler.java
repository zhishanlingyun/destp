package com.destp.spring.xml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by Administrator on 2017/1/2 0002.
 */
public class UserTagsHandler extends NamespaceHandlerSupport {

    public void init() {
        this.registerBeanDefinitionParser("user",new UserBeanDefinitionParser());
    }
}
