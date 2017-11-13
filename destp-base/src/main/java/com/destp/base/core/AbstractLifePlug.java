package com.destp.base.core;

import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zsly on 17-11-10.
 */
public abstract class AbstractLifePlug implements Lifecycle,Plug{

    private static final Logger log = LoggerFactory.getLogger(AbstractLifePlug.class);

    protected Lock lock = new ReentrantLock();

    private volatile boolean running;

    protected volatile LifeState lifeState = LifeState.Stop;

    private String appName;

    public AbstractLifePlug() {
    }

    public AbstractLifePlug(String appName) {
        this.appName = appName;
    }

    public void start() {
        lifeState = LifeState.Ready;
        startReady();
        if(running){
            throw new IllegalStateException(getName()+"已经运行中,不能重复启动!");
        }
        lock.lock();
        try {
            running = true;
            lifeState = LifeState.Running;
        } finally {
            lock.unlock();
        }
        log.info(getName()+"开始运行...");
        doStart();
    }

    public void stop() {
        stopReady();
        if(!running){
            return;
        }
        lock.lock();
        running = false;
        doStop();
        lifeState = LifeState.Stop;
    }

    public void pause() {
        throw new IllegalArgumentException("pause");
    }

    public void restart() {
        throw new IllegalArgumentException("restart");
    }

    public boolean isRunning() {
        return running;
    }

    public LifeState getState() {
        return lifeState;
    }

    protected void startReady(){}

    protected abstract void doStart();

    protected void stopReady(){}

    protected abstract void doStop();

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getName() {
        return this.appName==null?ClassUtils.getShortClassName(getClass()):appName;
    }
}
