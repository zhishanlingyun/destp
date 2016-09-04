package com.destp.scarebuy;

import com.destp.base.net.http.HttpWapper;
import com.destp.base.parse.HtmlParse;
import com.destp.base.parse.JsParse;
import com.destp.base.util.DateUtil;
import com.google.common.io.Resources;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/17 0017.
 */
public class JdCartScareBuy extends ScareBuyService {

    private static final Logger log = Logger.getLogger(JdCartScareBuy.class);

    private int[] buyTime ;

    @Override
    public Map prepareBuy(String reqUrl) {
        HttpWapper httpWapper = new HttpWapper();
        CookieStore cookieStore = httpWapper.loadCookies(cookiePath);
        String page = httpWapper.doGet(reqUrl, cookieStore, null);
        Map<String,String> map = HtmlParse.getPageValById(page,
                "paymentId",
                "isUsedVirtualPay",
                "securityPayPassword",
                "stockStatus", "sid", "pageSnapshotId", "flowTypeId",
                "isMixPayMentId", "orderKeyUrlId", "orderArrayId", "orderDateId",
                "orderKeyId", "isOpenDefenceUrlId", "submitOrderStr");
        System.out.println(map);
        String orderurl = scarePost(map);
        orderurl="http://p.m.jd.com/"+orderurl;
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("kopage",orderurl);
        result.put("ck",cookieStore);
        return result;
    }

    @Override
    public void koBuy(Map map) {
        if (null != map && !map.isEmpty()) {
            HttpWapper httpWapper = new HttpWapper();
            String url = (String) map.get("kopage");
            CookieStore cookieStore = (CookieStore) map.get("ck");
            String result = httpWapper.doPost(url, cookieStore, null);
            log.info("抢购"+result);
        }
    }

    @Override
    public boolean startBuy() {
        Date scarebuytime = new DateTime(buyTime[0], buyTime[1], buyTime[2], buyTime[3], buyTime[4], buyTime[5]).toDate();
        HttpWapper httpWapper = new HttpWapper();
        long diff;
        Date serverDate = null;
        do{
            HttpResponse response = httpWapper.get(this.getTimeUrl(), null, null);
            Header[] headers = response.getHeaders("Date");
            for(Header header:headers){
                serverDate = DateUtil.stduToDate(header.getValue());
            }
            diff = DateUtil.diffDate(scarebuytime, serverDate);

        }while (diff>400);
        System.out.println("服务器时间 "+DateUtil.dateToString(serverDate));
        System.out.println("相差"+diff);
        return true;
    }

    public static String scarePost(Map<String,String> attrMap){
        String a = "/norder/checkAndSubmitOrder.json?fm=order";
        String checkArray = attrMap.get("orderArrayId");
        String j = attrMap.get("orderDateId");
        String l = "";
        String c = attrMap.get("sid");
        String m = "235bab205babc43b138b226babc43b138b226babc";
        String[] cg = checkArray.split("_");
        int n = Integer.parseInt(cg[cg.length - 1]);
        String h = cg[0] + "" + cg[n] + "" + n;
        h = cg[0] + "" + cg[n] + "" + n;
        l = h + "_" + j + "_" + c + "_" + m;
        String url = JsParse.runJs(Resources.getResource("scarebuy.js"), "ShieldEncoder", l, "li43fevlisdfil234li");
        log.info(Resources.getResource("scarebuy.js"));
        String[] g = new String[]{"&", "m", "R", "d", "m", "="};
        String o = g[0] + g[1] + g[2];
        o += g[4] + g[3] + g[5];
        a += o + url;
        String k = attrMap.get("submitOrderStr");
        a += "&tk1=" + k;
        return a;
    }

    @Override
    public void init() {
        buyTime = getScareBuyTime();
    }

    public static void main(String[] args){
        /*JdCartScareBuy buy = new JdCartScareBuy();
        buy.setTimeUrl("http://p.m.jd.com/cart/cart.action?sid=cc720b281f0e95398a81ec5c30740c50");
        buy.setScareBuyTime("2016-7-17-20-0-0");
        buy.scareBuy("http://p.m.jd.com/norder/order.action?enterOrder=true&sid=cc720b281f0e95398a81ec5c30740c50");*/

        URL u =  Resources.getResource("scarebuy.js");
        String url = null;
        try {
            url = JsParse.runJs(u, "ShieldEncoder", "", "li43fevlisdfil234li");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(url);

    }
}
