package com.destp.base.parallel;

import java.util.Map;

/**
 * Created by Administrator on 2016/7/16 0016.
 */
public interface RunnableHandler {

    public Map doBefore();

    public void doWorker(Map map);

    public void doAfter();

    public boolean start();

    public void doExcepter();

}
