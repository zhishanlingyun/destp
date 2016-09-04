package com.dk;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: liuli10
 * Date: 15-8-24
 * Time: 下午2:44
 * To change this template use File | Settings | File Templates.
 */
public class Daka {

    public static Map getLoginCookie(String url,Map<String, String> parmas){
        Map map = new HashMap();
        HttpPost httpPost = new HttpPost(url);
        ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        if (parmas != null) {
            Set<String> keys = parmas.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                String key = (String) i.next();
                pairs.add(new BasicNameValuePair(key, parmas.get(key)));
            }
        }
        BasicHttpParams httpParams = new BasicHttpParams();
        DefaultHttpClient httpclient = new DefaultHttpClient(httpParams);
        UrlEncodedFormEntity p_entity;
        try {
            p_entity = new UrlEncodedFormEntity(pairs, "utf-8");
            httpPost.setEntity(p_entity);
            HttpResponse response = httpclient.execute(httpPost);
            map.put("cookie", httpclient.getCookieStore());
            map.put("content",ResposeToStrng(response.getEntity().getContent()));
            return map;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doGet(String url,Map<String, String> parmas,CookieStore cookie){
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
        HttpConnectionParams.setSoTimeout(httpParams, 10000);
        DefaultHttpClient httpclient = new DefaultHttpClient(httpParams);
        HttpGet httpGet = new HttpGet(url);
        ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        if (parmas != null) {
            Set<String> keys = parmas.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                String key = (String) i.next();
                httpParams.setParameter(key, parmas.get(key));
            }
        }
        try {
            httpGet.setParams(httpParams);
            if(null!=cookie){
                httpclient.setCookieStore(cookie);
            }
            HttpResponse response = httpclient.execute(httpGet);
            System.out.println(response);
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            return IOUtils.toString(content, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            httpclient.getConnectionManager().shutdown();
        }
        return null;
    }

    public static String doPost(String url,Map<String, String> parmas,CookieStore cookie){
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
        HttpConnectionParams.setSoTimeout(httpParams, 10000);
        DefaultHttpClient httpclient = new DefaultHttpClient(httpParams);
        HttpPost httpPost = new HttpPost(url);
        ArrayList<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
        if (parmas != null) {
            Set<String> keys = parmas.keySet();
            for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                String key = (String) i.next();
                pairs.add(new BasicNameValuePair(key, parmas.get(key)));
            }
        }
        try {
            UrlEncodedFormEntity p_entity = new UrlEncodedFormEntity(pairs, "utf-8");
            httpPost.setEntity(p_entity);
            httpclient.setCookieStore(cookie);
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            InputStream content = entity.getContent();
            return ResposeToStrng(content);
        } catch (Exception e) {
            throw new RuntimeException("daka fail");
        }finally{
            httpclient.getConnectionManager().shutdown();
        }
    }

    public static String toDK(String username,String pwd){Map<String,String> map = new HashMap<String,String>();
        map.put("Name",username);
        map.put("Password",pwd);
        map.put("Logon","登  录");
        map.put("__VIEWSTATE","/wEPDwUKLTQ2ODYwNTE3NmRkIbNHY3T5Mqr6jz6ORILXKQAAAAA=");
        map.put("__EVENTVALIDATION","/wEWBAKno/TmDAKbufQdAtLF4JEPAu/P+d0FCpA84heSt2TTTEI8kk7OLwAAAAA=");
        Map result =Daka.getLoginCookie("http://erp1.jd.com/newHrm/Verify.aspx?returnUrl=http%3a%2f%2ferp.jd.com%2findex.tpsml",map);
        CookieStore cookie = (CookieStore)result.get("cookie");
        String html = Daka.doGet("http://kaoqin.jd.com/",null,cookie);
        Document doc = Jsoup.parse(html);
        String tsDate = doc.getElementById("ts_now").val();
        String tsDateDes = doc.getElementById("ts_now_des").val();
        Map<String,String> dk = new HashMap<String,String>();
        dk.put("tsDate",tsDate);
        dk.put("tsDateDes",tsDateDes);
        String dkresult = Daka.doPost("http://kaoqin.jd.com/kaoqin/checkIn",dk,cookie);
        return dkresult;
    }

    public static String toDK(CookieStore cookieStore){
        String html = Daka.doGet("http://kaoqin.jd.com/",null,cookieStore);
        Document doc = Jsoup.parse(html);
        String tsDate = doc.getElementById("ts_now").val();
        String tsDateDes = doc.getElementById("ts_now_des").val();
        Map<String,String> dk = new HashMap<String,String>();
        dk.put("tsDate",tsDate);
        dk.put("tsDateDes",tsDateDes);
        String dkresult = Daka.doPost("http://kaoqin.jd.com/kaoqin/checkIn",dk,cookieStore);
        return dkresult;
    }

    public static void post(Map<String,String> map){
        try {
            DefaultHttpClient  client = new DefaultHttpClient();
            HttpPost post = new HttpPost(map.get("url"));
            List<NameValuePair> params=new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Name","liuli208"));
            params.add(new BasicNameValuePair("Password","JYcx2017@qq"));
            params.add(new BasicNameValuePair("Logon","登  录"));
            //params.add(new BasicNameValuePair("erp","liuli208"));
            params.add(new BasicNameValuePair("__VIEWSTATE","/wEPDwUKLTQ2ODYwNTE3NmRkIbNHY3T5Mqr6jz6ORILXKQAAAAA="));
            params.add(new BasicNameValuePair("__EVENTVALIDATION","/wEWBAKno/TmDAKbufQdAtLF4JEPAu/P+d0FCpA84heSt2TTTEI8kk7OLwAAAAA="));
            //params.add(new BasicNameValuePair("url","http%3a%2f%2ferp.jd.com%2findex.tpsml"));
            HttpEntity httpentity=new UrlEncodedFormEntity(params,"utf-8");
            post.setEntity(httpentity);
            post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            System.out.println(ResposeToStrng(entity.getContent()));
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void sendMail(String msg){
        /*JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.sina.com");
        sender.setUsername("liuli307@sina.com");
        sender.setPassword("011752zslyabc");
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setFrom(sender.getUsername());
        smm.setTo("sender.getUsername()");
        smm.setSubject("京东消息");
        smm.setText(msg);
        sender.send(smm);
        System.out.println("发送一条邮件");*/
    }

    public static String ResposeToStrng(InputStream inputStream){
        try {
            if(null != inputStream){
                ByteArrayOutputStream baos   =   new   ByteArrayOutputStream();
                int   i=-1;
                while((i=inputStream.read())!=-1){
                    baos.write(i);
                }
                return   baos.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public static void main(String[] args){
       /* try {
            String result = Daka.toDK("liuli208","JYcx2015@qq");
            System.out.println(result);
        } catch (Exception e) {

        }*/
        Calendar c = Calendar.getInstance();
        c.set(2016,6,5,22,10,13);
        Date date = c.getTime();
        System.out.println(c.getTime().getTime());
        Timer timer = new Timer();
        DkTask dt = new DkTask();
        timer.schedule(dt,date);
        Calendar c2 = Calendar.getInstance();
        c2.set(2016,6,6,9,10,16);
        Date date2 = c2.getTime();
        System.out.println(c2.getTime().getTime());
        DkTask dt2 = new DkTask();
        Timer timer2 = new Timer();
        timer2.schedule(dt2,date2);
    }

    static class DkTask extends TimerTask{
        long muinte = 1000*60;
        @Override
        public void run() {
            boolean success = false;
            while(!success){
                try {
                    String result = Daka.toDK("liuli208","JYcx2017@qq");
                    //String result = Daka.toDK("guishaozhun","Xueer521131$");
                    System.out.println(result);
                    success = true;
                    //System.exit(0);
                } catch (Exception e) {
                    try {
                        Thread.sleep(3*muinte);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }

        }
    }

}
