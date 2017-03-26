package com.destp.common;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发阻塞调用工具类
 * Created by yfzhangchao@jd.com on 2015/5/30 0030.
 */
public class ConcurrentExecutor {
    //共享线程池
    private static ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(8);
    protected static Logger log = Logger.getLogger(ConcurrentExecutor.class);
    /**
     * @param concurrentHandler 业务处理接口
     * @param workerNum         并发worker线程数量
     * @param concurrent        是否以并发方式调用 任务数量少的时候 并发的系统开销要大于串行for循环
     * @param timeout           超时时间
     * @param unit              超时时间
     */
    public static void submit(final ConcurrentHandler concurrentHandler, int workerNum, boolean concurrent, long timeout, TimeUnit unit, boolean wait) {
        long now=System .currentTimeMillis();
        if(concurrentHandler.getTask()==null||concurrentHandler.getTask().isEmpty()){
            return;
        }
        if (concurrent) {

            final CountDownLatch countDownLatch = new CountDownLatch(workerNum);
            final ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(
                    concurrentHandler.getTask().size(), true, concurrentHandler.getTask());
            final AtomicInteger total = new AtomicInteger(arrayBlockingQueue.size());
            final AtomicInteger success = new AtomicInteger(0);
            for (int i = 0; i < workerNum; i++) {
                scheduledExecutorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            Object task = arrayBlockingQueue.poll();
                            try {
                                if (task == null) {
                                    break;
                                } else {
                                    concurrentHandler.handle(task);
                                    success.incrementAndGet();
                                }
                            } catch (Throwable e) {
                                log.error(e.getMessage(),e);
                                try {
                                    concurrentHandler.exceptionHandle(task, e);
                                    success.incrementAndGet();
                                    continue;
                                } catch (Throwable throwable) {
                                    log.error(throwable.getMessage(), e);
                                    arrayBlockingQueue.clear();
                                    break;
                                }


                            }
                        }
                        countDownLatch.countDown();
                    }
                });
            }
            try {
                if (wait) {
                    countDownLatch.await(timeout, unit);
                    Assert.isTrue(success.get()==total.get());
                }


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            for (Object task : concurrentHandler.getTask()) {
                try {
                    concurrentHandler.handle(task);
                } catch (Throwable throwable) {
                    concurrentHandler.exceptionHandle(task, throwable);
                    continue;
                }
            }
        }
        log.info("ConcurrentExecutor submit done , concurrent:"+concurrent+ " cost:"+(System.currentTimeMillis()-now)+" millis");
    }


}