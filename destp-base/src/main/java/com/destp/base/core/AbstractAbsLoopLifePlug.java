package com.destp.base.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zsly on 17-11-11.
 */
public abstract class AbstractAbsLoopLifePlug extends AbstractAsyLifePlug implements LoopMethod{

    private static final Logger log = LoggerFactory.getLogger(AbstractAbsLoopLifePlug.class);

    @Override
    protected void doStart() {
        Runnable runnable = new Runnable() {
            public void run() {
                while(isRunning()){
                    if(isPause()){
                        doPause();
                    }
                    loop();
                }
                log.error(getName()+" 结束...");
            }
        };
        init(runnable);
        exe.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                log.error("线程 {} 异常",t.getName(),e);
            }
        });
        exe.start();
    }
}
