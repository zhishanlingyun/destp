package com.destp.base.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;

/**
 * Created by zsly on 17-11-11.
 */
public abstract class AbstractPauseAbleLifePlug extends AbstractLifePlug{

    private static final Logger log = LoggerFactory.getLogger(AbstractPauseAbleLifePlug.class);

    private Condition condition = lock.newCondition();

    public AbstractPauseAbleLifePlug() {
        super();
    }

    public AbstractPauseAbleLifePlug(String appName) {
        super(appName);
    }

    @Override
    public void pause() {
        lifeState = LifeState.Pause;
    }

    protected void doPause() {
        lock.lock();
        try {
            lifeState = LifeState.Pause;
            log.info(getName()+"暂停...");
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void restart() {
        lock.lock();
        try {
            condition.signalAll();
            lifeState = LifeState.Running;
            log.info(getName()+"恢复...");
        } finally {
            lock.unlock();
        }
    }

    public boolean isPause(){
        return LifeState.Pause.equals(lifeState);
    }

}
