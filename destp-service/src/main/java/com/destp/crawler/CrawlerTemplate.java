package com.destp.crawler;

import com.destp.Lifecycle;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Administrator on 2016/10/4 0004.
 */
public class CrawlerTemplate implements Lifecycle{

    private static final Logger log = Logger.getLogger(CrawlerTemplate.class);

    private volatile boolean running;

    private ArrayBlockingQueue queue;

    public void collect(String address,RuleCrawler ruleCrawler){
        String host = address;
        while(running){
            List<PageLink> pageLinkList = ruleCrawler.getPageLinkList(host);
            if(CollectionUtils.isEmpty(pageLinkList)){
                break;
            }
            try{
                for(PageLink pk:pageLinkList){
                    queue.put(pk);
                }
            }catch (InterruptedException e){
                log.error("InterruptedException...");
            }
            host = ruleCrawler.getNextHost(host);
        }
    }

    public void init(){
        running = true;
    }


    public void start() {

    }

    public void pause() {

    }

    public void stop() {

    }
}
