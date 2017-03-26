package com.destp.ood.obj;


import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 360buy
 * Date: 2012-3-1
 * Time: 19:58:45
 * 促销发布的JQ消息.
 */
public class PromotionJQ extends PromotionJQProperty {
    private String promoId;//促销编号
    private String promoType;//促销类型
    private String productId;//商品编号
    private String discount;//返现
    private String jbean;//京豆
    private String timeBegin;//开始时间
    private String timeend;//结束时间
    private String exttype;//扩展类型
    private List<String> products;//套装商品主商品
    private String memberlevel;//单品促销会员级别

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId;
    }

    public String getPromoType() {
        return promoType;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeend() {
        return timeend;
    }

    public void setTimeend(String timeend) {
        this.timeend = timeend;
    }

    public String getExttype() {
        return exttype;
    }

    public void setExttype(String exttype) {
        this.exttype = exttype;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public String getMemberlevel() {
        return memberlevel;
    }

    public void setMemberlevel(String memberlevel) {
        this.memberlevel = memberlevel;
    }

    public String getJbean() {
        return jbean;
    }

    public void setJbean(String jbean) {
        this.jbean = jbean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PromotionJQ that = (PromotionJQ) o;

        if (promoId != null ? !promoId.equals(that.promoId) : that.promoId != null) return false;
        if (promoType != null ? !promoType.equals(that.promoType) : that.promoType != null) return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (discount != null ? !discount.equals(that.discount) : that.discount != null) return false;
        if (jbean != null ? !jbean.equals(that.jbean) : that.jbean != null) return false;
        if (timeBegin != null ? !timeBegin.equals(that.timeBegin) : that.timeBegin != null) return false;
        if (timeend != null ? !timeend.equals(that.timeend) : that.timeend != null) return false;
        if (exttype != null ? !exttype.equals(that.exttype) : that.exttype != null) return false;
        if (products != null ? !products.equals(that.products) : that.products != null) return false;
        if (memberlevel != null ? !memberlevel.equals(that.memberlevel) : that.memberlevel != null) return false;
        if(null!=this.getProperty()&&null!=that.getProperty()){
            if(this.getProperty().size()!=that.getProperty().size()){
                return false;
            }
            for(String key : this.getProperty().keySet()){
                if("timeStamp".equals(key)){
                    continue;
                }
                if(!this.getProperty().get(key).equals(that.getProperty().get(key))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = promoId != null ? promoId.hashCode() : 0;
        result = 31 * result + (promoType != null ? promoType.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (jbean != null ? jbean.hashCode() : 0);
        result = 31 * result + (timeBegin != null ? timeBegin.hashCode() : 0);
        result = 31 * result + (timeend != null ? timeend.hashCode() : 0);
        result = 31 * result + (exttype != null ? exttype.hashCode() : 0);
        result = 31 * result + (products != null ? products.hashCode() : 0);
        result = 31 * result + (memberlevel != null ? memberlevel.hashCode() : 0);
        return result;
    }

    public static void main(String[] args){
        String s1 = "{\"exttype\":\"2\",\"products\":[],\"promoId\":\"4184135927\",\"promoType\":\"6\",\"property\":{\"100\":[\"1\"],\"101\":[\"1\"],\"115\":[\"3\"],\"200\":[\"{\\\"1148624929\\\":{\\\"dis\\\":\\\"103.00\\\",\\\"plshow\\\":\\\"1\\\",\\\"sku\\\":\\\"[{\\\\\\\"id\\\\\\\":\\\\\\\"1281712209\\\\\\\",\\\\\\\"num\\\\\\\":\\\\\\\"1\\\\\\\"}]\\\"},\\\"1157795550\\\":{\\\"dis\\\":\\\"53.00\\\",\\\"plshow\\\":\\\"1\\\",\\\"sku\\\":\\\"[{\\\\\\\"id\\\\\\\":\\\\\\\"1309294804\\\\\\\",\\\\\\\"num\\\\\\\":\\\\\\\"1\\\\\\\"}]\\\"}}\"],\"timeStamp\":[\"2017-03-15 00:19:06\"]},\"timeBegin\":\"2017-03-15 00:22:00\",\"timeend\":\"2017-09-05 23:36:00\"}";
        String s2 = "{\"exttype\":\"2\",\"products\":[],\"promoId\":\"4184135927\",\"promoType\":\"6\",\"property\":{\"100\":[\"1\"],\"101\":[\"1\"],\"115\":[\"3\"],\"200\":[\"{\\\"1148624929\\\":{\\\"dis\\\":\\\"103.00\\\",\\\"plshow\\\":\\\"1\\\",\\\"sku\\\":\\\"[{\\\\\\\"id\\\\\\\":\\\\\\\"1281712209\\\\\\\",\\\\\\\"num\\\\\\\":\\\\\\\"1\\\\\\\"}]\\\"},\\\"1157795550\\\":{\\\"dis\\\":\\\"53.00\\\",\\\"plshow\\\":\\\"1\\\",\\\"sku\\\":\\\"[{\\\\\\\"id\\\\\\\":\\\\\\\"1309294804\\\\\\\",\\\\\\\"num\\\\\\\":\\\\\\\"1\\\\\\\"}]\\\"}}\"],\"timeStamp\":[\"2017-03-15 00:19:00\"]},\"timeBegin\":\"2017-03-15 00:22:00\",\"timeend\":\"2017-09-05 23:36:00\"}";
        PromotionJQ p1 = JSONParserSingleton.toObject(s1,PromotionJQ.class);
        PromotionJQ p2 = JSONParserSingleton.toObject(s2,PromotionJQ.class);
        System.out.println(ObjectUtils.equals(p1,p2));
    }
}
