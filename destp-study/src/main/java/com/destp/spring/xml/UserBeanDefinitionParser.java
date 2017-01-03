package com.destp.spring.xml;

import com.destp.common.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * Created by Administrator on 2017/1/2 0002.
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String id =  element.getAttribute("id");
        String name = element.getAttribute("name");
        builder.addPropertyValue("id",Long.valueOf(id));
        builder.addPropertyValue("name",name);
    }
}
