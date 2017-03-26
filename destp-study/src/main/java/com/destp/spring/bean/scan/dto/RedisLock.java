package com.destp.spring.bean.scan.dto;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public class RedisLock implements Lock{

    private String key;

    private int expire;

    public RedisLock() {
    }

    public RedisLock(String key, int expire) {
        this.key = key;
        this.expire = expire;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    @Override
    public String getLockKey() {
        return key;
    }

    @Override
    public int expire() {
        return expire;
    }


}
