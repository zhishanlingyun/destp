package com.destp.scarebuy;

import com.destp.base.net.http.HttpWapper;
import com.destp.base.parallel.CountDownThreadProcess;
import com.destp.base.parallel.RunnableHandler;
import com.destp.base.parse.HtmlParse;
import org.apache.http.client.CookieStore;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;


/**
 * Created by Administrator on 2016/7/3 0003.
 */
public abstract class ScareBuyService {

    private static final Logger log = Logger.getLogger(ScareBuyService.class);

    protected String cookiePath = "D:\\\\data\\\\cookies1.txt";

    public abstract Map prepareBuy(String reqUrl);

    private String scareBuyTime;

    private String timeUrl;



    public void koBuy(Map map){
//        String resp = parseSubmitUrl(page,cookieStore);
        //log.info(resp);
    }

    public boolean startBuy(){
        return false;
    }

    public void init(){

    }

    public void scareBuy(final String url){
        init();
        CountDownThreadProcess.process(10, new RunnableHandler() {
            public void doWorker(Map map) {
                koBuy(map);
            }

            public boolean start() {
                return startBuy();
            }

            public void doExcepter() {

            }

            public Map doBefore() {
                return prepareBuy(url);
            }

            public void doAfter() {

            }
        });
    }

    public String getCookiePath() {
        return cookiePath;
    }

    public void setCookiePath(String cookiePath) {
        this.cookiePath = cookiePath;
    }

    public String getTimeUrl() {
        return timeUrl;
    }

    public void setTimeUrl(String timeUrl) {
        this.timeUrl = timeUrl;
    }

    public int[] getScareBuyTime() {
        if(StringUtils.isEmpty(this.scareBuyTime)){
            throw new RuntimeException("没有设置抢购时间");
        }
        String[] scareBuyTime = this.scareBuyTime.split("-");
        int[] result = new int[scareBuyTime.length];
        for(int i=0;i<scareBuyTime.length;i++){
            result[i] = Integer.parseInt(scareBuyTime[i]);
        }
        return result;
    }

    public void setScareBuyTime(String scareBuyTime) {
        this.scareBuyTime = scareBuyTime;
    }
}
