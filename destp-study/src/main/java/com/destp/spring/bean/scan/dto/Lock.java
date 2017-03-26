package com.destp.spring.bean.scan.dto;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public interface Lock {

    public String getLockKey();

    public int expire();

}
