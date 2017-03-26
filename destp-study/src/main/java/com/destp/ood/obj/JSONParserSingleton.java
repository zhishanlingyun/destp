package com.destp.ood.obj;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * Created by IntelliJ IDEA. User: 360buy Date: 2011-12-28 Time: 21:59:32 To
 * change this template use File | Settings | File Templates.
 */

public class JSONParserSingleton {

    private JSONParserSingleton() {
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        T t = JSON.parseObject(json, clazz);
        return t;
    }
    public static <T> T fromJson(String json,TypeReference<T> typeReference){
        return JSON.parseObject(json,typeReference);
    }
    public static String toJSONString(Object obj) {
        String json = JSON.toJSONString(obj);
        return json;
    }

    public static <T> List<T> fromListJson(String json, Class clazz){
        return  JSON.parseArray(json,clazz);
    }

}
