package com.destp.spring.spel;

import org.springframework.expression.ParserContext;

/**
 * Created by Administrator on 2017/3/25 0025.
 */
public class MyParserContext implements ParserContext {

    public MyParserContext() {
    }

    @Override
    public boolean isTemplate() {
        return true;
    }

    @Override
    public String getExpressionPrefix() {
        return "${";
    }

    @Override
    public String getExpressionSuffix() {
        return "}";
    }
}
