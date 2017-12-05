package com.destp.base.net.client.builder.provider;

import com.destp.base.net.client.Cookie;
import com.destp.base.net.client.builder.CookieBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.impl.cookie.BasicClientCookie;

public class DefCookieBuilder implements CookieBuilder {

    @Override
    public Cookie build(Cookie cookie) {
        BasicClientCookie ck = new BasicClientCookie(cookie.getName(),cookie.getValue());
        ck.setDomain(cookie.getDomain());
        if(StringUtils.isEmpty(cookie.getPath())){
            ck.setPath("/");
        }else {
            ck.setPath(cookie.getPath());
        }
        ck.setAttribute(ClientCookie.DOMAIN_ATTR,cookie.getDomain());
        cookie.setCookie(ck);
        return cookie;
    }
}
