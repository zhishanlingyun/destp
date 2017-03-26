/*
import com.jd.compare.frame.*;
import com.jd.compare.impl.compareware.*;
import com.jd.compare.impl.event.fina.PoolEvent;
import com.jd.compare.impl.event.fina.PromoEvent;
import com.jd.compare.impl.event.fina.UEvent;
import com.jd.compare.impl.event.pre.PriceTaskEvent;
import com.jd.compare.impl.event.pre.PromoTaskEvent;
import com.jd.compare.impl.origin.RedisOrigin;
import com.jd.compare.impl.precompareware.PriceTaskPreCompareWare;
import com.jd.compare.impl.precompareware.PromoTaskPreCompareWare;
import com.jd.fastjson.JSONArray;
import com.jd.promotionrelease.domain.redisjson.PriceIndexSubValueObj;
import com.jd.promotionrelease.domain.redisjson.PromotionDanpinObj;
import com.jd.promotionrelease.domain.util.JSONParserSingleton;
import com.jd.promotionrelease.domain.util.KeyUtils;
import com.jd.promotiontaskworker.manager.PromotionReleaseNewManager;
import com.jd.promotiontaskworker.service.PromotionInfoNewBuilderService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.SystemUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

*/
/**
 * Created by liuli10 on 2016/12/6.
 *//*

public class CompareTest {

    //@Test
    */
/*public void eqPD1(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        RedisOrigin std = new RedisOrigin();
        std.setOriginName("standard");
        std.setStandard(true);
        std.setPromotionReleaseNewManager((PromotionReleaseNewManager)context.getBean("promotionReleaseNewManager"));

        RedisOrigin o1 = new RedisOrigin();
        o1.setOriginName("center1");
        o1.setPromotionReleaseNewManager((PromotionReleaseNewManager)context.getBean("promotionReleaseNewManager2"));

        RedisOrigin o2 = new RedisOrigin();
        o2.setOriginName("center2");
        o2.setPromotionReleaseNewManager((PromotionReleaseNewManager)context.getBean("promotionReleaseNewManager2"));

        List<Origin> others = new ArrayList<Origin>();
        others.add(o1);
        others.add(o2);
        OriginWapper ow = new OriginWapper(std,others);

        CompareWare cw = new PromoCompareWare();
        cw.setOriginWapper(ow);

        CompareProcess cp = new CompareProcess();
        cp.registerCompareWare(cw);

        Event event = new PromoEvent();
        event.setAttribute(Constant.EVENT_ATTR_KEY,"J_10314870_1_PD");
        cp.compare(event);

    }
*//*

    */
/*@Test
    public void eqTask(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        RedisOrigin std = new RedisOrigin();
        std.setOriginName("standard");
        std.setStandard(true);
        std.setPromotionReleaseNewManager((PromotionReleaseNewManager)context.getBean("promotionReleaseNewManager"));

        RedisOrigin o1 = new RedisOrigin();
        o1.setOriginName("center1");
        o1.setPromotionReleaseNewManager((PromotionReleaseNewManager)context.getBean("promotionReleaseNewManager2"));

        RedisOrigin o2 = new RedisOrigin();
        o2.setOriginName("center2");
        o2.setPromotionReleaseNewManager((PromotionReleaseNewManager)context.getBean("promotionReleaseNewManager2"));

        List<Origin> others = new ArrayList<Origin>();
        others.add(o1);
        others.add(o2);
        OriginWapper ow = new OriginWapper(std,others);

        CompareWare cw = new PromoCompareWare();
        cw.setOriginWapper(ow);

        UCompareWare ucp = new UCompareWare();
        ucp.setOriginWapper(ow);

        CVPCompareWare cvp = new CVPCompareWare();
        cvp.setOriginWapper(ow);
        CompareProcess cp = new CompareProcess();
        AreaCompareWare ac = new AreaCompareWare();
        ac.setOriginWapper(ow);
        PoolCompareWare pw = new PoolCompareWare();
        pw.setOriginWapper(ow);
        SkuCompareWare scw = new SkuCompareWare();
        scw.setOriginWapper(ow);

        cp.registerCompareWare(cw);
        cp.registerCompareWare(ucp);
        cp.registerCompareWare(cvp);
        cp.registerCompareWare(ac);
        cp.registerCompareWare(pw);
        cp.registerCompareWare(scw);
        PromoTaskPreCompareWare pcw = new PromoTaskPreCompareWare();
        pcw.setPromotionInfoNewBuilderService((PromotionInfoNewBuilderService)context.getBean("promotionInfoNewBuilderServiceWapper"));
        cp.registerPreCompareWare(pcw);

        PriceTaskPreCompareWare ptp = new PriceTaskPreCompareWare();
        cp.registerPreCompareWare(ptp);



        Event event = new PromoTaskEvent(13960L,6);
        //Event event = new PriceTaskEvent(752711L);

        //event.setAttribute(Constant.EVENT_ATTR_KEY,"J_10314870_1_PD");
        cp.compare(event);
    }*//*


    //@Test
    public void beanTest(){
        PromoEvent e1 = new PromoEvent();
        e1.setPromoid(1L);
        e1.setPromotype(1);
        e1.setAttribute("a",1);
        Event e2 = null;
        */
/*try {
            e2 = (Event) BeanUtils.cloneBean(e1);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }*//*

        e2 = (Event) SerializationUtils.clone(e1);
        System.out.print(e2);
        e1.setPromoid(9L);
        e2.setAttribute("b",2);
        System.out.print(e2);
        System.out.print(e1);
    }

    @Test
    public void jsonTest(){
        //String json = "{p={\"price\":\"80.0000\",\"reward\":\"0.0000\",\"salesprice\":\"100.0000\"}, un=[{\"e\":\"1483113600000\",\"id\":\"10314870\",\"s\":\"1480493561203\",\"t\":1}]}";
        //String json = "J_1809897111_SKU :hash-{3687866711_6={\"et\":\"1482979440000\",\"pi\":\"1310257223\",\"st\":\"1480231260000\"}, 3756250335_6={\"et\":\"1483413360000\",\"pi\":\"1310257223\",\"st\":\"1481100420000\"}, 2548757430_6={\"et\":\"1462415820000\",\"pi\":\"1310257223\",\"st\":\"1460876160000\"}}";
        */
/*Map<String, Object> map = new HashMap<String, Object>();

        // convert JSON string to Map
        try {
            map = mapper.readValue(json, new TypeReference<Map<String, Object>>(){});
        } catch (Exception e) {
            e.printStackTrace();
        }*//*


        */
/*json = json.substring(1,json.length()-1);

        StringTokenizer st = new StringTokenizer(json," ");
        while(st.hasMoreElements()){
            String str = st.nextToken();
            if(str.endsWith(",")){
                str = str.substring(0,str.length()-1);
            }
            System.out.println(str);
        }*//*

        //Map<String,String> map = StringHelper.string2map(json);
        */
/*String json = "[12,34,56,67,87]";
        List<String> list = JSONArray.parseArray(json,String.class);*//*

        */
/*Man m = new Man();
        m.setA("abc");
        m.setB(6);
        List<Man> list = new ArrayList<Man>();
        list.add(m);
        String json = JSONParserSingleton.toJSONString(list);
        System.out.println(json);
        List m1 = JSONArray.parseArray(json,Man.class);
        System.out.println(m1);*//*

        */
/*String j1 ="{\"areacode\":[\"6\",\"544\",\"3\",\"10\",\"4\",\"321\",\"432\",\"543\",\"600\"],\"areas\":[],\"areatype\":0,\"begintime\":\"1307674800000\",\"blanks\":[],\"canusedq\":\"1\",\"endtime\":\"1559869200000\",\"exttype\":\"0\",\"limitperiod\":\"0\",\"limittimes\":\"0\",\"limitusertype\":\"0\",\"maxnum\":\"5\",\"member\":[{\"coupon\":[],\"discount\":\"532.0000\",\"gift\":[],\"memberlevel\":\"50\",\"optionalgiftnum\":\"0\",\"price\":\"9499.0000\"}],\"minnum\":\"0\",\"promotionid\":\"10506863\",\"salesnum\":\"0\",\"submitflag\":\"1\",\"token\":{},\"type\":\"1\"}";
        String j2 = "{\"areacode\":[\"6\",\"544\",\"3\",\"10\",\"4\",\"321\",\"432\",\"543\",\"600\"],\"areas\":[],\"areatype\":0,\"begintime\":\"1307674800000\",\"blanks\":[],\"canusedq\":\"1\",\"endtime\":\"1559869200000\",\"exttype\":\"0\",\"limitusertype\":\"0\",\"maxnum\":\"5\",\"member\":[{\"coupon\":[],\"discount\":\"532.00\",\"gift\":[],\"memberlevel\":\"50\",\"optionalgiftnum\":\"0\",\"price\":\"0.00\"}],\"minnum\":\"0\",\"phoneLogo\":\"1\",\"pol\":[\"1\"],\"promotionid\":\"10506863\",\"salesnum\":\"0\",\"submitflag\":\"1\",\"token\":{},\"type\":\"1\"}";
        PromotionDanpinObj d1 = JSONParserSingleton.toObject(j1,PromotionDanpinObj.class);
        PromotionDanpinObj d2 = JSONParserSingleton.toObject(j2,PromotionDanpinObj.class);
        System.out.println(d1.equals(d2));*//*

       */
/* A a1 = new A();
        a1.setA(10);
        a1.setName("1");
        A a2 = new A();
        a2.setA(10);
        a2.setName("1");
        System.out.println(a1.equals(a2));*//*

        */
/*PriceIndexSubValueObj p = new PriceIndexSubValueObj();
        p.setE("1");
        p.setId("aaa");
        p.setPno("2");
        p.setT(1);
        String json = JSONParserSingleton.toJSONString(p);
        System.out.println(json);*//*


        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        String json = JSONParserSingleton.toJSONString(list);
        System.out.println(json);
        List<String> l = JSONParserSingleton.fromListJson(json,String.class);
        for (String s : l){
            System.out.println(s);
        }

        */
/*String json = "{\"e\":\"1\",\"endTime\":1,\"id\":\"aaa\",\"key\":\"aaa\",\"pno\":\"2\",\"t\":1}";
        PriceIndexSubValueObj p = JSONParserSingleton.toObject(json,PriceIndexSubValueObj.class);
        System.out.println(p);*//*

    }

    //@Test
    public void timeTest(){
        */
/*long cur = System.currentTimeMillis();
        System.out.println(cur);
        Long l = new Long("1483113600000");
        System.out.println((l-cur));*//*


        System.out.println(KeyUtils.getPromoidFromPack("J_1282750610_3756338400_POOL"));
    }

    //@Test
    public void objTest(){
        PriceIndexSubValueObj obj1 = new PriceIndexSubValueObj();
        obj1.setE("q");
        PriceIndexSubValueObj obj2 = new PriceIndexSubValueObj();
        System.out.println(obj1.equals(obj2));
    }

    @Test
    public void eqPD(){
        String s1 = "J_188596252_1_PD :-list-[{\"areacode\":[\"6\",\"3\",\"10\",\"4\"],\"areas\":[],\"areatype\":0,\"begintime\":\"1461200400000\",\"blanks\":[],\"canusedq\":\"1\",\"cfprice\":\"0.00\",\"cprice\":\"3699.00\",\"endtime\":\"1490403600000\",\"exttype\":\"0\",\"limitusertype\":\"0\",\"maxnum\":\"0\",\"member\":[{\"coupon\":[],\"discount\":\"0.00\",\"fixedprice\":\"3299.00\",\"gift\":[],\"memberlevel\":\"50\",\"optionalgiftnum\":\"0\",\"price\":\"0.00\"}],\"minnum\":\"0\",\"overlyingSuit\":\"0\",\"phoneLogo\":\"1\",\"pol\":[\"4\"],\"promoTags\":[2],\"promotionid\":\"188596252\",\"salesnum\":\"0\",\"submitflag\":\"1\",\"token\":{\"tokenid\":\"58398\",\"tokennum\":\"1\"},\"type\":\"1\"}]";
        String s2 = "J_188596252_1_PD :-list-[{\"adwordurl\":\"\",\"areacode\":[\"6\",\"3\",\"10\",\"4\"],\"areas\":[],\"areatype\":0,\"begintime\":\"1461200400000\",\"blanks\":[],\"canusedq\":\"1\",\"cfprice\":\"0.00\",\"cprice\":\"3699.00\",\"endtime\":\"1490403600000\",\"exttype\":\"0\",\"limitusertype\":\"0\",\"maxnum\":\"0\",\"member\":[{\"coupon\":[],\"discount\":\"0.00\",\"fixedprice\":\"3299.00\",\"gift\":[],\"memberlevel\":\"50\",\"optionalgiftnum\":\"0\",\"price\":\"0.00\"}],\"minnum\":\"0\",\"overlyingSuit\":\"0\",\"phoneLogo\":\"1\",\"pol\":[\"4\"],\"promoTags\":[2],\"promotionid\":\"188596252\",\"salesnum\":\"0\",\"submitflag\":\"1\",\"token\":{\"tokenid\":\"58398\",\"tokennum\":\"1\"},\"type\":\"1\"}]";
        Event stde = new PromoEvent(188596252L,1);
        stde.setContent(s1);
        CompareObj std = new CompareObj("1",stde);

        Event otre = new PromoEvent(188596252L,1);
        otre.setContent(s2);
        CompareObj otr = new CompareObj("2",otre);

        //UCompareWare u = new UCompareWare();
        PromoCompareWare u = new PromoCompareWare();
        System.out.println((u.compare(std,otr)));

    }

    @Test
    public void eqU(){
        String s1 = "J_1591997_U:-hash-{\"p\":\"{\"price\":\"999.0000\",\"reward\":\"0.0000\",\"salesprice\":\"1099.0000\"}\",\"un\":\"[{\"e\":\"1483199940000\",\"id\":\"189237285\",\"s\":\"1464231062807\",\"t\":1},{\"e\":\"1483199999000\",\"id\":\"194406992\",\"s\":\"1480521600000\",\"t\":1},{\"e\":\"1483199999000\",\"id\":\"195184228\",\"s\":\"1482336000000\",\"t\":1},{\"e\":\"1483200000000\",\"id\":\"195195474\",\"s\":\"1482249600000\",\"t\":1},{\"e\":\"1482335999000\",\"id\":\"195177742\",\"s\":\"1482163200000\",\"t\":1},{\"e\":\"1482335999000\",\"id\":\"195106148\",\"s\":\"1482076800000\",\"t\":1}]\",\"wh\":\"[{\"e\":\"1482335999000\",\"id\":\"195113291\",\"s\":\"1482076800000\",\"t\":10}]\",\"wh\":\"[{\"e\":\"1482335999000\",\"id\":\"195113291\",\"s\":\"1482076800000\",\"t\":10}]\"}";
        String s2 = "J_1591997_U:-hash-{\"p\":\"{\"price\":\"999.0000\",\"reward\":\"0.0000\",\"salesprice\":\"1099.0000\"}\",\"un\":\"[{\"e\":\"1483199940000\",\"id\":\"189237285\",\"s\":\"1464231062807\",\"t\":1},{\"e\":\"1483199999000\",\"id\":\"194406992\",\"s\":\"1480521600000\",\"t\":1},{\"e\":\"1482335999000\",\"id\":\"195106148\",\"s\":\"1482076800000\",\"t\":1},{\"e\":\"1483199999000\",\"id\":\"195184228\",\"s\":\"1482336000000\",\"t\":1},{\"e\":\"1482335999000\",\"id\":\"195177742\",\"s\":\"1482163200000\",\"t\":1}]\",\"wh\":\"[{\"e\":\"1482335999000\",\"id\":\"195113291\",\"s\":\"1482076800000\",\"t\":10}]\"}";

        Event stdevent = new UEvent(1591997L);
        stdevent.setContent(s1);
        CompareObj std = new CompareObj("1",stdevent);
        Event otrevent = new UEvent(1591997L);
        otrevent.setContent(s2);
        CompareObj otr = new CompareObj("2",otrevent);
        UCompareWare u = new UCompareWare();
        System.out.println((u.compare(std,otr)));

    }

    @Test
    public void json(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("wh","[{\"e\":\"1482335999000\",\"id\":\"195113291\",\"s\":\"1482076800000\",\"t\":10}]");
        map.put("p","{\"price\":\"999.0000\",\"reward\":\"0.0000\",\"salesprice\":\"1099.0000\"}");
        map.put("un","[{\"e\":\"1483199940000\",\"id\":\"189237285\",\"s\":\"1464231062807\",\"t\":1},{\"e\":\"1483199999000\",\"id\":\"194406992\",\"s\":\"1480521600000\",\"t\":1},{\"e\":\"1482335999000\",\"id\":\"195106148\",\"s\":\"1482076800000\",\"t\":1},{\"e\":\"1483199999000\",\"id\":\"195184228\",\"s\":\"1482336000000\",\"t\":1},{\"e\":\"1482335999000\",\"id\":\"195177742\",\"s\":\"1482163200000\",\"t\":1}]");
        String json = JSONParserSingleton.toJSONString(map);
        System.out.println(json);
    }
}
*/
