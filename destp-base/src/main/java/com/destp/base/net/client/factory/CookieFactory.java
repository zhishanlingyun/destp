package com.destp.base.net.client.factory;

import com.destp.base.net.client.Cookie;
import com.destp.base.net.client.builder.CookieBuilder;
import com.destp.base.net.client.builder.provider.DefCookieBuilder;

import java.util.HashMap;
import java.util.Map;

public class CookieFactory {

    private static Map<String,CookieBuilder> buildMap = new HashMap<String, CookieBuilder>();

    static {
        buildMap.put("def",new DefCookieBuilder());
    }

    public static Cookie create(String type,Cookie ck){
        return buildMap.get(type).build(ck);
    }

}
