package com.destp.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/2 0002.
 */
public class PageService {

    public Map<String,String> getPageValById(String html,String... ids){
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
