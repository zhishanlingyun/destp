package core;

import com.destp.base.net.client.Cookie;
import com.destp.base.net.client.IhttpClient;
import com.destp.base.net.client.NConfig;
import com.destp.base.net.client.NRequest;
import com.destp.base.net.client.provider.DefaultHttpClient;
import com.destp.base.util.DateUtil;
import jodd.io.StreamUtil;
import jodd.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTimeUtils;
import org.junit.Test;

import java.io.*;
import java.util.Date;
import java.util.List;

public class Http {

    @Test
    public void testget(){
        String url = "http://i.baidu.com/";
        //String url = "https://www.baidu.com/";
        NConfig config = new NConfig();
        config.setPoolMaxConnTotal(5);
        config.setPoolConnPerRoute(5);
        config.setKeepAliveTime(1000);
        BasicClientCookie ck1 = new BasicClientCookie("BDUSS","Q0R0hEVjBidnc2Q1lBUVVGLWduR2YyclAtcDMtY3c3UFBJbHdmZzFQVjRPMHBhQVFBQUFBJCQAAAAAAAAAAAEAAACAsg-UeXJnajEyMwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHiuIlp4riJaN1");
        ck1.setDomain("baidu.com");
        ck1.setPath("/");
        ck1.setAttribute(ClientCookie.DOMAIN_ATTR,"baidu.com");
        config.setLogCookie(new Cookie(ck1));
        IhttpClient httpClient = new DefaultHttpClient().config(config);
        NRequest request = new NRequest(url);
        /*ck1.setExpiryDate(DateUtil.setDate("2026-02-18 00:00:00.000Z"));
        //ck1.setDomain("baidu.com");
        ck1.setPath("/");
        ck1.setAttribute(ClientCookie.DOMAIN_ATTR,"baidu.com");
        Cookie cookie = new Cookie();
        cookie.setCookie(ck1);*/
        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        InputStream in = httpClient.Get(request);
        try {
            List<String> lines = IOUtils.readLines(in,"UTF-8");
            StringBuilder sb = new StringBuilder();
            for(String line : lines){
                sb.append(line).append("\n");
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        String url = "http://a.hiphotos.baidu.com/image/pic/item/50da81cb39dbb6fda2d331e50324ab18962b376d.jpg";
        IhttpClient httpclient = new DefaultHttpClient().config(null);
        NRequest request = new NRequest(url);
        request.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        InputStream in = httpclient.Get(request);
        try {
            FileUtils.copyToFile(in,new File("E:\\test\\b.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
