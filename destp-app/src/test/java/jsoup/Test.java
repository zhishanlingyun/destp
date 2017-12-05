package jsoup;

import com.destp.base.net.client.Cookie;
import com.destp.base.net.client.IhttpClient;
import com.destp.base.net.client.NConfig;
import com.destp.base.net.client.factory.CookieFactory;
import com.destp.base.net.client.provider.DefaultHttpClient;
import com.destp.base.util.CommonUtils;
import destp.app.crawler.config.CronConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.junit.Before;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class Test {

    IhttpClient client;

    @Before
    public void setup(){
        NConfig config = new NConfig();
        Cookie cookie = new Cookie();
        cookie.setName( CronConfig.getConfig().getString("cookie.login.key"));
        cookie.setValue(CronConfig.getConfig().getString("cookie.login.value"));
        cookie.setDomain(CronConfig.getConfig().getString("cookie.login.domain"));
        cookie = CookieFactory.create("def",cookie);
        config.setLogCookie(cookie);
        client = new DefaultHttpClient().config(config);
    }

    @org.junit.Test
    public void test1(){
        InputStream in = client.Get("http://source.jd.com/app/pms/blob/master/end-delimiter/promotionapp-manage-data/src/main/java/com/jd/promo/Catalog.java");
        String ctx = null;
        try {
            ctx = CommonUtils.toString(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document doc = Jsoup.parse(ctx);
        //Element content = doc.getElementById("code_content");
        Element code = doc.select("#fileContentContainer").first();
        System.out.println(code.textNodes().get(0).getWholeText());
        String result = Jsoup.clean(code.text(), "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
        //System.out.println(result);
        //System.out.println(content.toString());
        /*Elements elements = content.getElementsByTag("a");
        Iterator<Element> it = elements.iterator();
        while (it.hasNext()){
            System.out.println(it.next().attr("href"));
        }*/
        /*Iterator<Element> it = content.select("a[href]").iterator();
        while (it.hasNext()){
            System.out.println(it.next().attr("href"));
        }*/


    }


    @org.junit.Test
    public void test2(){
        String[] urls = {
                "/app/pms/tree/master/end-delimiter/promotionapp-manage-service",
                "/app/pms/tree/master/end-delimiter/promotionapp-manage-vo",
                "/app/pms/blob/master/end-delimiter/.gitignore",
                "/app/pms/blob/master/end-delimiter/README.md",
                "/app/pms/blob/master/end-delimiter/pom.xml",
                ".."
        };

        for(String url : urls){
            if(url.lastIndexOf(".")!=-1&&url.lastIndexOf("..")==-1){
                System.out.println(url);
            }
        }

        for(String url :urls){
            System.out.println(StringUtils.substringAfter(url,"/app/pms/blob/master/end-delimiter"));
        }




    }


}
