package com.destp.base.net.client;

import java.io.InputStream;

/**
 * http客户端请求
 */
public interface IhttpClient {

    /**
     * 初始化配置
     * @return
     */
    public IhttpClient config(NConfig config);

    public InputStream Get(String url);

    public InputStream Get(NRequest request);

    public InputStream Post(NRequest request);

    public void close();
}
