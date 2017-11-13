package com.destp.base.core;

/**
 * Created by zsly on 17-11-10.
 */
public interface Lifecycle {

    public void start();

    public void stop();

    public void pause();

    public void restart();

    public boolean isRunning();

    public LifeState getState();

}
