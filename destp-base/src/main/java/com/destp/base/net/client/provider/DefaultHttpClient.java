package com.destp.base.net.client.provider;

import com.destp.base.convert.Convert;
import com.destp.base.net.client.Cookie;
import com.destp.base.net.client.IhttpClient;
import com.destp.base.net.client.NConfig;
import com.destp.base.net.client.NRequest;
import com.destp.base.util.CommonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DefaultHttpClient implements IhttpClient {

    private Logger logger = LoggerFactory.getLogger(DefaultHttpClient.class);

    private CloseableHttpClient httpClient;

    @Override
    public IhttpClient config(NConfig config) {
        if(null==config){
            config = new NConfig();
        }
        HttpClientBuilder builder = HttpClients.custom();
        if(0<config.getKeepAliveTime()){
            builder.setConnectionTimeToLive(config.getKeepAliveTime(), TimeUnit.MILLISECONDS);
        }
        if(config.getPoolMaxConnTotal()>0){
            builder.setMaxConnTotal(config.getPoolMaxConnTotal());
        }
        if(config.getPoolConnPerRoute()>0){
            builder.setMaxConnTotal(config.getPoolConnPerRoute());
        }
        if(null!=config.getLogCookie()){
            CookieStore cookieStore = new BasicCookieStore();
            cookieStore.addCookie((org.apache.http.cookie.Cookie) config.getLogCookie().getCookie());
            builder.setDefaultCookieStore(cookieStore);
        }
        httpClient = builder.build();
        logger.info("httpclient build success...");
        return this;
    }

    @Override
    public InputStream Get(String url) {
        HttpGet get = new HttpGet(url);
        BufferedHttpEntity entity = null;
        try {
            get.addHeader(new BasicHeader("User-Agent","\"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36"));
            HttpResponse response = httpClient.execute(get);
            entity = new BufferedHttpEntity(response.getEntity());
            return entity.getContent();
        } catch (IOException e) {
            logger.error("get requset fail",e);
        }
        return null;
    }

    @Override
    public InputStream Get(NRequest request) {
        HttpGet get = new HttpGet(request.getUrl());
        List<Header> headers = convert(request);
        if(CollectionUtils.isNotEmpty(headers)){
            get.setHeaders(headers.toArray(new Header[0]));
        }
        HttpContext httpContext = null;
        List<Cookie> cookies = request.getCookies();
        if(CollectionUtils.isNotEmpty(cookies)){
            CookieStore cookieStore = new BasicCookieStore();
            for(Cookie cookie : cookies){
                cookieStore.addCookie((org.apache.http.cookie.Cookie)cookie.getCookie());
            }
            httpContext = new BasicHttpContext();
            httpContext.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);
        }
        HttpResponse response = null;
        try {
            response = httpClient.execute(get,httpContext);
            return response.getEntity().getContent();
        } catch (IOException e) {
            logger.error("get fail",e);
        }
        return null;
    }

    @Override
    public InputStream Post(NRequest request) {
        return null;
    }

    @Override
    public void close() {

    }

    private List<Header> convert(NRequest request){
        if(!MapUtils.isEmpty(request.getHeaders())){
            return CommonUtils.convert(request.getHeaders(), new Convert<Header>() {
                @Override
                public Header convert(Object... objects) {
                    Header header = new BasicHeader((String) objects[0],(String) objects[1]);
                    return header;
                }
            });
        }
        return null;
    }


}
