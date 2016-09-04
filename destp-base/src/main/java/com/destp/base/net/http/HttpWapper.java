package com.destp.base.net.http;

import com.destp.base.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class HttpWapper {

    private static Logger log = Logger.getLogger(HttpWapper.class);

    public CookieStore loadCookies(String parh){
        File file = new File(parh);
        List<String> lines = null;
        try {
            lines = FileUtils.readLines(file, "UTF-8");
        } catch (IOException e) {
            log.error("加载cookie文件失败",e.getCause());
        }
        CookieStore cookieStore = new BasicCookieStore();
        for(String line : lines){
            String[] c = line.split("\\t");
            BasicClientCookie cookie = new BasicClientCookie(c[c.length-2],c[c.length-1]);
            cookie.setDomain(c[0]);
            cookie.setPath(c[2]);
            log.debug("加载"+cookie);
            cookieStore.addCookie(cookie);
        }
        return cookieStore;
    }

    public HttpResponse get(String url,CookieStore cookieStore,Map<String,String> param){
        HttpResponse response = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpUriRequest get = new HttpGet(new URI(url));
            HttpClientContext context = new HttpClientContext();
            if(null!=cookieStore){
                context.setCookieStore(cookieStore);
            }
            if(null!=param&&!param.isEmpty()){
                List<NameValuePair> params = paramMapToNameValuePairList(param);
                for(NameValuePair np : params){
                    context.setAttribute(np.getName(),np.getValue());
                }
            }
            response = client.execute(get, context);
        } catch (URISyntaxException e) {
            log.error(e);
        }catch (IOException e1){
            log.error(e1);
        }
        return response;
    }

    public HttpResponse post(String url,CookieStore cookieStore,Map<String,String> param){
        HttpResponse response = null;
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(new URI(url));
            HttpClientContext context = new HttpClientContext();
            if(null!=cookieStore){
                context.setCookieStore(cookieStore);
            }
            if(null!=param&&!param.isEmpty()){
                post.setEntity(new UrlEncodedFormEntity(paramMapToNameValuePairList(param)));
            }
            response = client.execute(post,context);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e1){
            e1.printStackTrace();
        }catch (IOException e2){
            e2.printStackTrace();
        }
        return response;
    }

    public String doGet(String url,CookieStore cookieStore,Map<String,String> param){
        HttpResponse response = null;
        String resp = null;
        response = this.get(url,cookieStore,param);
        try {
            resp = IOUtils.toString(response.getEntity().getContent(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            log.error(e);
        }
        log.debug(resp);
        return resp;
    }

    public String doPost(String url,CookieStore cookieStore,Map<String,String> param){
        HttpResponse response = null;
        response = post(url,cookieStore,param);
        String resp = null;
        try {
            resp = IOUtils.toString(response.getEntity().getContent(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            log.error(e);
        }
        log.debug(resp);
        return resp;
    }

    private List<NameValuePair> paramMapToNameValuePairList(Map<String,String> map){
        List<NameValuePair> result = null;
        if(null!=map&&!map.isEmpty()){
            result = new ArrayList<NameValuePair>();
            Set<Map.Entry<String,String>> set = map.entrySet();
            for(Map.Entry<String,String> entry : set){
                NameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(),entry.getValue());
                result.add(nameValuePair);
            }
        }
        return null;
    }

    public static void main(String[] args){
        HttpWapper wapper = new HttpWapper();
        HttpResponse response = wapper.get("http://st.360buyimg.com/order/css/base.css?v=20160705", null, null);
        for(Header header :response.getHeaders("Date")){
            System.out.println(DateUtil.dateToString(DateUtil.stduToDate(header.getValue())));
        }

    }



}
