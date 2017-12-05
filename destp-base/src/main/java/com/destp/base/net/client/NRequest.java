package com.destp.base.net.client;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * http请求
 * 非线程安全类
 */
public class NRequest {

    //请求地址
    private String url;

    //请求头
    private Map<String,String> headers = new LinkedHashMap<String,String>();

    //请求参数
    private Map params = new LinkedHashMap<String,Object>();

    private List<Cookie> cookies = new ArrayList<Cookie>();

    public NRequest() {}

    public NRequest(String url) {
        this.url = url;
    }

    public NRequest addHeader(String name,String value){
        headers.put(name,value);
        return this;
    }

    public NRequest addParams(Object name,Object value){
        params.put(name,value);
        return this;
    }

    public NRequest addCookie(Cookie cookie){
        cookies.add(cookie);
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map getParams() {
        return params;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }
}
