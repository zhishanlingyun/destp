package com.destp.common;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.commons.collections.map.HashedMap;

import java.lang.reflect.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

/**
 * Created by Administrator on 2016/12/14 0014.
 */
public class ObjectUtil {

    public static final Set<Class> baseClass = new HashSet<Class>();

    static {
        baseClass.add(boolean.class);
        baseClass.add(Boolean.class);
        baseClass.add(short.class);
        baseClass.add(Short.class);
        baseClass.add(byte.class);
        baseClass.add(Byte.class);
        baseClass.add(int.class);
        baseClass.add(Integer.class);
        baseClass.add(float.class);
        baseClass.add(Float.class);
        baseClass.add(long.class);
        baseClass.add(Long.class);
        baseClass.add(double.class);
        baseClass.add(Double.class);
        baseClass.add(String.class);
        baseClass.add(char.class);
    }

    public static void comp(Class clazz,Object obj,Object other){
        if(null==obj&&null==other) return;
        if(obj.getClass()!=clazz && other.getClass()!=clazz) return;

        Field[] fields = clazz.getDeclaredFields();
        try {
            for(Field f : fields){
                System.out.println("type : {} "+f.getType().getName());
                if(f.getType().isAssignableFrom(List.class)){
                    Type type = f.getGenericType();
                    System.out.println("@@@@@@@ "+type +"\t"+(type instanceof GenericArrayType)+"\t"+(type instanceof ParameterizedType));
                    if(type instanceof ParameterizedType){
                        //泛型
                        ParameterizedType pt = (ParameterizedType) type;
                        System.out.println("getOwnerType {} = "+pt.getOwnerType()+"\t"+"getRawType {} = "+pt.getRawType()+"\t"+pt.getActualTypeArguments()[0]);
                        Type[] types = pt.getActualTypeArguments();
                        System.out.println("******** "+types.length);
                        Object o = clazz.getDeclaredMethod(getMethod(f.getName())).invoke(obj);
                        Class clz = (Class) pt.getActualTypeArguments()[0];
                        System.out.println("------- "+clz);
                        if(baseClass.contains(clz)){
                            List list = (List) o;
                            for(Object ob : list){
                                System.out.println("^^^^^^^^ "+ob);
                            }
                        }else {
                            List list = (List) o;
                            for(Object ob : list){
                                System.out.println("^^^^^^^^ "+ob);
                                Field[] fs = clz.getDeclaredFields();
                                for(Field field : fs){
                                    System.out.println("<><><><><> "+field.getName()+"\t"+clz.getDeclaredMethod(getMethod(field.getName())).invoke(ob));
                                    //comp(field.getType(),clazz.getDeclaredMethod(getMethod(field.getName())).invoke(ob));
                                }
                            }
                        }
                    }
                }
                if(baseClass.contains(f.getType())){
                    System.out.println(f.getDeclaringClass()+"\t"+f.getName()+" = "+clazz.getDeclaredMethod(getMethod(f.getName())).invoke(obj));

                }/*else if(f.getType()  Collection){

                }*/else {
                    //comp(f.getType(),clazz.getDeclaredMethod(getMethod(f.getName())).invoke(obj));
                }

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static boolean compare(Class clazz,Object obj,Object other){
        boolean result = false;
        if(null==obj&&null==other) return true;
        if(obj.getClass()!=clazz && other.getClass()!=clazz) return false;

        Field[] fields = clazz.getDeclaredFields();
        try {
            for(Field f : fields){
                if(f.getType().isAssignableFrom(List.class)){
                    Type type = f.getGenericType();
                    if(type instanceof ParameterizedType){
                        //泛型
                        ParameterizedType pt = (ParameterizedType) type;
                        Type[] types = pt.getActualTypeArguments();

                        Class clz = (Class) pt.getActualTypeArguments()[0];
                        System.out.println("------- "+clz);
                        Object o1 = clazz.getDeclaredMethod(getMethod(f.getName())).invoke(obj);
                        Object o2 = clazz.getDeclaredMethod(getMethod(f.getName())).invoke(other);
                        if(isAllNull(o1,o2)) {
                            return true;
                        }
                        if(hasNull(o1,o2)){
                            System.out.println("---exist null value");
                            return false;
                        }
                        if(baseClass.contains(clz)){
                            boolean succ = compareListBase((List)o1,(List)o2);
                            System.out.println("@@@ 列表比较结果: "+succ);
                            //return succ;
                        }else {
                            //对于复杂类型的泛型list比较，可以采用先计算每个对象的校验和，将结果放入set,用另外List元素的校验和扫描这个set,如果全部命中，说明两个list相等
                            List list1 = (List) o1;
                            List list2 = (List) o2;
                            String crc = null;
                            System.out.println(list1);
                            System.out.println(list2);
                            /*Set<String> tmp = new HashSet<String>();
                            for(int i=0;i<list1.size();i++){
                                Field[] fs = clz.getDeclaredFields();
                                for(Field field : fs){
                                    if(baseClass.contains(field.getType())){
                                        crc += checkSum(clz,field,list1.get(i));

                                    }else{
                                        //递归

                                    }
                                    System.out.println("<><><><><> "+field.getName()+"\t"+clz.getDeclaredMethod(getMethod(field.getName())).invoke(list1.get(i)));
                                    compare(field.getType(),clz.getDeclaredMethod(getMethod(field.getName())).invoke(list1.get(i)),clz.getDeclaredMethod(getMethod(field.getName())).invoke(list2.get(i)));
                                }
                                crc = getMD5(crc);
                                tmp.add(crc);
                            }*/
                        }
                    }else{
                        System.out.println("-------非泛型-------");
                    }
                }
                if(baseClass.contains(f.getType())){
                    Object o1 = clazz.getDeclaredMethod(getMethod(f.getName())).invoke(obj);
                    Object o2 = clazz.getDeclaredMethod(getMethod(f.getName())).invoke(other);
                    System.out.println("比较字段名称\t"+getMethodName(f)+"\t比较类型: "+f.getType()+"\to1 = "+o1+"\t"+"o2 = "+o2+"\t o1 eq o2 = "+(o1.equals(o2)));
                    if(!o1.equals(o2)){
                        return false;
                    }
                }else {
                    Object o1 = clazz.getDeclaredMethod(getMethod(f.getName())).invoke(obj);
                    Object o2 = clazz.getDeclaredMethod(getMethod(f.getName())).invoke(other);
                    if(hasNull(o1,o2)){
                        System.out.println("比较字段名称\t"+getMethodName(f)+"\t---比较类型: "+f.getType()+"\to1 = "+o1+"\t"+"o2 = "+o2+"\t o1 eq o2 = "+(o1.equals(o2)));
                    }else {
                        result = compare(f.getType(),o1,o2);
                    }
                }

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return true;
    }

    /*public static String checkSum(Class clazz,Field field,Object obj){
        long sum = 0L;
        String md = null;
        if(baseClass.contains(clazz)){
            //基本类型，则计算校验和
            try {
                Object o = clazz.getDeclaredMethod(getMethod(field.getName())).invoke(obj);
                String s = String.valueOf(o);
                md = getMD5(s);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        return md;
    }*/


    public static String getMethod(String name){
        String _name = name;
        _name = _name.toLowerCase();
        _name = "get"+_name.substring(0,1).toUpperCase()+_name.substring(1);
        return _name;
    }

    public static String getMethodName(Field f){
        StringBuilder sb = new StringBuilder();
        sb.append(f.getDeclaringClass().getName()).append(".").append(f.getName());
        return sb.toString();
    }

    /*public static String getMD5(Object str) {
        try {
            if(!baseClass.contains(str))
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数

            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            throw new RuntimeException("MD5加密出现错误");
        }
    }*/

    public static boolean hasNull(Object... objs){
        for(Object o : objs){
            if(null==o){
                return true;
            }
        }
        return false;
    }

    public static boolean isAllNull(Object... objs){
        for(Object o : objs){
            if(null!=o){
                return false;
            }
        }
        return true;
    }

    //基本类型列表比较，可以使用简单的eq方法
    public static boolean compareListBase(List l1,List l2){
        if(l1.size()!=l2.size()) return false;
        HashSet temp = new HashSet();
        for(Object o : l1){
            System.out.println("列表1值 = "+o);
            temp.add(o);
        }
        for(Object o2 : l2){
            System.out.println("列表2值 = "+o2);
            if(!temp.contains(o2)){
                return false;
            }
        }
        return true;
    }

    /*public static boolean compareListComplexObj(List l1,List l2,Class clazz){
        Field[] fields = clazz.getDeclaredFields();
        Map<String,Object> tmp = new HashMap<String,Object>();
        for(Field f : fields){
            if(baseClass.contains(f.getType())){
                  for(Object o1 : l1){

                  }
            }
        }
    }*/

    public static void main(String[] args){
        /*User user = new User();
        user.setId(1000L);
        user.setName("zhangsan");
        Group group = new Group();
        group.setGid(2000L);
        group.setGname("youkezu");
        user.setGroup(group);
        List<String> friends = new ArrayList<String>();
        friends.add("zhangfei");
        friends.add("guanyu");
        user.setFriends(friends);
        List xqs = new ArrayList();
        xqs.add("pashan");
        xqs.add("youyong");
        user.setXq(xqs);
        Point p1 = new Point();
        p1.setX(1);
        p1.setY(1);
        Point p2 = new Point();
        p2.setX(2);
        p2.setY(2);
        List<Point> points = new ArrayList<Point>();
        points.add(p1);
        points.add(p2);
        user.setPoints(points);
        //comp(User.class,user);

        User user2 = new User();
        user2.setId(1000L);
        user2.setName("zhangsan");
        Group group2 = new Group();
        group2.setGid(2000L);
        group2.setGname("youkezu");
        user2.setGroup(group2);
        List<String> friends2 = new ArrayList<String>();
        friends2.add("zhangfei");
        friends2.add("guanyu");
        user2.setFriends(friends2);
        List xqs2 = new ArrayList();
        xqs2.add("pashan");
        xqs2.add("youyong");
        user2.setXq(xqs2);
        Point pp1 = new Point();
        pp1.setX(1);
        pp1.setY(1);
        Point pp2 = new Point();
        pp2.setX(2);
        pp2.setY(3);
        List<Point> points2 = new ArrayList<Point>();
        points2.add(pp1);
        points2.add(pp2);
        user2.setPoints(points2);

        boolean succ = compare(User.class,user,user2);*/
        /*System.out.println("结果 ： "+succ);
        System.out.println("对象1："+ JSON.toJSONString(user));
        System.out.println("对象2："+ JSON.toJSONString(user2));*/
        /*List<String> list = new ArrayList<String>();
        list.add("bbb");
        list.add("aca");
        list.add("adc");*/
        List<Point> list = new ArrayList<Point>();
        Point p1 = new Point(1,3);
        Point p2 = new Point(3,2);
        Point p3 = new Point(2,1);
        /*list.add(p1);
        list.add(p2);
        list.add(p3);
        Collections.sort(list);
        for(Point s : list){
            System.out.println(s);
        }*/
        System.out.println(p1.equals(p2));
        Set<Point> set = new HashSet<Point>();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        for(Point s : set){
            System.out.println(s);
        }
    }
}
