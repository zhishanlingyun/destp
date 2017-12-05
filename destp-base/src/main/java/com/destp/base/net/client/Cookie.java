package com.destp.base.net.client;

import java.util.Date;

public class Cookie<T> {

    private T cookie;

    private String name;

    private String value;

    private String path;

    private String domain;

    private Date expireTime;

    public boolean secure;

    public Cookie() {
    }

    public Cookie(T cookie) {
        this.cookie = cookie;
    }

    public T getCookie() {
        return cookie;
    }

    public void setCookie(T cookie) {
        this.cookie = cookie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isSecure() {
        return secure;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }
}
