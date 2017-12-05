package com.destp.base.net.client;

public class NConfig {

    private Cookie logCookie;

    //会话保持时间 单位毫秒
    private long keepAliveTime;

    //连接池最大连接数
    private int PoolMaxConnTotal = 5;

    //每个路由基础的连接
    private int PoolConnPerRoute = 2;

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getPoolMaxConnTotal() {
        return PoolMaxConnTotal;
    }

    public void setPoolMaxConnTotal(int poolMaxConnTotal) {
        PoolMaxConnTotal = poolMaxConnTotal;
    }

    public int getPoolConnPerRoute() {
        return PoolConnPerRoute;
    }

    public void setPoolConnPerRoute(int poolConnPerRoute) {
        PoolConnPerRoute = poolConnPerRoute;
    }

    public Cookie getLogCookie() {
        return logCookie;
    }

    public void setLogCookie(Cookie logCookie) {
        this.logCookie = logCookie;
    }
}
