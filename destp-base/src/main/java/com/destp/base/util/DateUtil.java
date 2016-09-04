package com.destp.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public abstract class DateUtil {

    private final static SimpleDateFormat stduDate = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss",Locale.UK);

    private final static SimpleDateFormat readDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date stduToDate(String date){
        try {
            stduDate.setTimeZone(TimeZone.getTimeZone("GMT"));
            return stduDate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String dateToString(Date date){
        return readDate.format(date);
    }

    public static String dateToString(long time){
        Date d = new Date(time);
        return readDate.format(d);
    }


    public static long diffDate(Date date1,Date date2){
        return (date1.getTime()-date2.getTime());
    }


    public static void main(String[] args){
        Date d = DateUtil.stduToDate("Sun, 17 Jul 2016 08:21:57 GMT");
        System.out.println(d);
        System.out.println(DateUtil.dateToString(d));
        System.out.println(Math.abs(diffDate(d, new Date())));
    }


}
