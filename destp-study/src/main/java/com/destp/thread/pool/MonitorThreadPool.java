package com.destp.thread.pool;

import org.apache.log4j.Logger;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by liuli10 on 2017/1/10.
 */
public class MonitorThreadPool extends ScheduledThreadPoolExecutor {

    private static final Logger log = Logger.getLogger(MonitorThreadPool.class);

    public MonitorThreadPool(int corePoolSize) {
        super(corePoolSize);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        log.info(t.getName()+" beforeExecute created!");
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        log.info(" afterExecute created!");
    }
}
