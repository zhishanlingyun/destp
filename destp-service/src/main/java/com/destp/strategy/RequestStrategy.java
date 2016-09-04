package com.destp.strategy;

import java.util.Map;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public interface RequestStrategy {

    public Map<String,String> buildRequestParam();

}
