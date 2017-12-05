package destp.app.crawler.jd.finder;

import com.destp.base.util.CommonUtils;
import destp.app.crawler.domain.Url;
import destp.app.crawler.search.Find;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JdFinder extends Find {

    public String getContent(InputStream in) {
        String html = null;
        try {
            html = CommonUtils.toString(in);
            Document doc = Jsoup.parse(html);
            Element content = doc.getElementById("code_content");
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Url> getNexts(String ctx) {
        List<Url> urlList = new ArrayList<Url>();
        Document doc = Jsoup.parse(ctx);
        Iterator<Element> it = doc.select("a[href]").iterator();
        String url = null;
        while (it.hasNext()){
            url = it.next().attr("href");
            if(CommonUtils.isFileUrl(url)){
                send(url);
            }
            urlList.add(new Url(url,false));
        }
        return urlList;
    }
}
