package com.destp.base.core;

/**
 * Created by zsly on 17-11-11.
 */
public abstract class AbstractAsyLifePlug extends AbstractPauseAbleLifePlug {

    protected Thread exe;

    public AbstractAsyLifePlug() {
        super();
    }

    public AbstractAsyLifePlug(Thread exe) {
        super();
        this.exe = exe;
    }

    public AbstractAsyLifePlug(String appName, Thread exe) {
        super(appName);
        this.exe = exe;
    }

    protected void init(Runnable runnable){
        if(null==runnable){
            throw new IllegalArgumentException("没有可运行的方法");
        }
        if(null==exe){
            exe = new Thread(runnable,"T-"+getName());
        }
    }

    public void setDaemon(boolean on){
        if(null==exe){
            throw new IllegalArgumentException("没有初始化异步线程");
        }
        exe.setDaemon(on);
    }

}
