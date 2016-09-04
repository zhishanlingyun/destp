package com.destp.base.parse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
public class HtmlParse {

    public static Map<String,String> getPageValById(String html,String... ids){
        Document doc = Jsoup.parse(html);
        Map<String,String> map = new HashMap<String, String>();
        for(String id : ids){
            Element elem = doc.getElementById(id);
            if(null!=elem){
                String val = elem.val();
                map.put(id,val);
            }
        }
        return map;
    }

}
