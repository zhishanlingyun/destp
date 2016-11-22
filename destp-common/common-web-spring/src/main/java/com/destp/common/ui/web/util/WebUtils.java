package com.destp.common.ui.web.util;

import com.destp.common.ui.web.view.RequestResult;

import javax.servlet.ServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/20 0020.
 */
public class WebUtils extends org.springframework.web.util.WebUtils {

    public static Map<String,Object> mergePageResult(ServletRequest request, RequestResult requestResult){
        Map<String,Object> map = new HashMap<String, Object>();
        if(null!=request){
            map.putAll(getRequestAttrMap(request));
        }
        if(null!=requestResult){
            map.putAll(requestResult.getModel());
        }
        return map;
    }

    public static Map<String,Object> getRequestAttrMap(ServletRequest request){
        Map<String,Object> map = new HashMap<String, Object>();
        Enumeration enu = request.getAttributeNames();
        if(null!=enu){
            while (enu.hasMoreElements()){
                String key = (String)enu.nextElement();
                map.put(key,request.getAttribute(key));
            }
        }
        return map;
    }


}
