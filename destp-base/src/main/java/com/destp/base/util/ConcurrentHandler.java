package com.destp.base.util;

import java.util.List;

/**
 * Created by yfzhangchao@jd.com on 2015/5/30 0030.
 */
public interface ConcurrentHandler<T> {
    void handle(T t);

    List<T> getTask();

    void exceptionHandle(T t, Throwable throwable);
}
