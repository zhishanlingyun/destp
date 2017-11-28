package destp.net;

import jodd.util.ThreadUtil;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.CloseableHttpPipeliningClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HttpclientTest {

    @Test
    public void quick(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = null;
        HttpEntity entity = null;               //当连接关闭时，读取不到内容
        BufferedHttpEntity bufentity = null;    //保存在内存中，当连接关闭时，仍然可以读取到内容
        try {
            response = httpclient.execute(httpGet);
            entity = response.getEntity();
            bufentity = new BufferedHttpEntity(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            /*BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("E:\\test\\bd.html")));
            entity.writeTo(out);
            out.flush();*/
            //System.out.println(EntityUtils.toString(entity));
            System.out.println(EntityUtils.toString(bufentity));

        } catch (Exception e) {
            e.printStackTrace();
        }
        /*try {
            HttpPost httpPost = new HttpPost("http://www.baidu.com");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "vip"));
            nvps.add(new BasicNameValuePair("password", "secret"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);
            System.out.println(response2.getStatusLine());
            HttpEntity entity2 = response2.getEntity();
            // do something useful with the response body
            // and ensure it is fully consumed
            EntityUtils.consume(entity2);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /*public void simpler(){
        Request.Get("http://targethost/homepage")
                .execute().returnContent();
        Request.Post("http://targethost/login")
                .bodyForm(Form.form().add("username",  "vip").add("password",  "secret").build())
                .execute().returnContent();
    }*/

    @Test
    public void uri(){
        try {
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("www.google.com")
                    .setPath("/search")
                    .setParameter("q", "httpclient")
                    .setParameter("btnG", "Google Search")
                    .setParameter("aq", "f")
                    .setParameter("oq", "")
                    .build();
            System.out.println(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void keep(){
        //本例是自定义了httpclient,更改了连接保持策略，满足个性化要求
        ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {

            @Override
            public long getKeepAliveDuration(
                    HttpResponse response,
                    HttpContext context) {
                long keepAlive = super.getKeepAliveDuration(response, context);
                System.out.println(keepAlive);
                if (keepAlive == -1) {
                    // Keep connections alive 5 seconds if a keep-alive value
                    // has not be explicitly set by the server
                    keepAlive = 5000;
                }
                return keepAlive;
            }

        };
        CloseableHttpClient httpclient = HttpClients.custom()
                .setKeepAliveStrategy(keepAliveStrat)
                .build();
        HttpGet get = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = null;
        BufferedHttpEntity entity = null;
        try {
            response = httpclient.execute(get);
            entity = new BufferedHttpEntity(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println(EntityUtils.toString(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pool(){
        ConnectionConfig connectionConfig = ConnectionConfig.custom()
                .setCharset(Consts.UTF_8)
                .build();
        PoolingHttpClientConnectionManager pm = new PoolingHttpClientConnectionManager();
        pm.setDefaultMaxPerRoute(5);
        pm.setMaxTotal(20);
        pm.setDefaultConnectionConfig(connectionConfig);
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setConnectionManager(pm);
        CloseableHttpClient httpClient = builder.build();
        HttpGet get = new HttpGet("https://www.baidu.com");
        HttpResponse response = null;
        BufferedHttpEntity entity = null;
        try {
            response = httpClient.execute(get);
            entity = new BufferedHttpEntity(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(EntityUtils.toString(entity));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sy(){
        HttpResponse response = null;
        HttpGet get = new HttpGet("http://www.baidu.com");
        FutureCallback<HttpResponse> callback = new FutureCallback<HttpResponse>(){
            public void completed(HttpResponse result) {

               System.out.println("completed");

            }

            public void failed(Exception ex) {

            }

            public void cancelled() {

            }
        };
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().build();
        httpclient.start();
        Future<HttpResponse> responseFuture = httpclient.execute(get, null);
        try {
            response = responseFuture.get();
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
