package com.destp.ood.obj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/25 0025.
 */
public class PromotionJQProperty {
    public static final String PROPERTY_CODE_AREA_TAG = "100";
    public static final String PROPERTY_CODE_AREA_TAG_ALL = "1";
    public static final String PROPERTY_CODE_AREA_TAG_WHITE = "2";
    public static final String PROPERTY_CODE_AREA_TAG_BLACK = "3";
    public static final String PROPERTY_CODE_WEBSITES = "101";
    public static final String PROPERTY_CODE_FIXEDPRICE = "103";
    public static final String PROPERTY_CODE_PROMO_TAGS_QQMEMBER = "104";
    public static final String PROPERTY_CODE_SHOPMEMBERDISCOUNT = "105";
    public static final String PROPERTY_CODE_NEWUSERPRICE = "106";              //新人专享价标志
    public static final String PROPEREY_CODE_PACK = "200";                      //套装扩展属性标志
    public static final String PROPEREY_CODE_PACK_SKU = "sku";                  //套装扩展中商品列表标志
    public static final String PROPEREY_CODE_PACK_DISCOUNT = "dis";             //套装扩展中套装池基础优惠价标志
    public static final String PROPEREY_CODE_PACK_SHOW = "plshow";              //套装扩展中套装池展示标志

    private Map<String, List<String>> property;

    public Map<String, List<String>> getProperty() {
        return property;
    }

    public void setProperty(Map<String, List<String>> property) {
        this.property = property;
    }

    public void addProperty(String key, List<String> value) {
        if (this.getProperty() == null) {
            this.property = new HashMap<String, List<String>>();
        }
        this.property.put(key, value);
    }

    public void addProperty(  List<String> area, List<String> blackArea) {
        List<String> value=new ArrayList<String>();
        String tag=PROPERTY_CODE_AREA_TAG_ALL;
        if(area!=null&&!area.isEmpty()){
            tag=PROPERTY_CODE_AREA_TAG_WHITE;
        }else if(blackArea!=null&&!blackArea.isEmpty()){
            tag=PROPERTY_CODE_AREA_TAG_BLACK;
        }
        this.addProperty(PROPERTY_CODE_AREA_TAG, tag);
    }

    public void addProperty(String key, String value) {
        if (this.getProperty() == null) {
            this.property = new HashMap<String, List<String>>();
        }
        if (this.getProperty().get(key) == null) {
            this.getProperty().put(key, new ArrayList<String>());
        }
        this.getProperty().get(key).add(value);
    }
}
