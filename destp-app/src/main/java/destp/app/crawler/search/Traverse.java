package destp.app.crawler.search;

import com.destp.base.core.AbstractAsyLifePlug;
import destp.app.crawler.domain.Url;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zsly on 17-11-11.
 * 遍历策略
 */
public class Traverse extends AbstractAsyLifePlug {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Find finder;

    private BlockingQueue<Url> queue;

    private Url seed;

    public Traverse(Find finder) {
        this.finder = finder;
    }

    public Traverse(Find finder, BlockingQueue<Url> queue, Url seed) {
        this.finder = finder;
        this.queue = queue;
        this.seed = seed;
    }

    public Traverse() {
    }

    protected void doStart() {
        Runnable runnable = new Runnable() {
            public void run() {
                traver(seed);
            }
        };
        super.init(runnable);
        exe.start();
    }

    public void setQueue(BlockingQueue<Url> queue) {
        this.queue = queue;
    }

    public void setFinder(Find finder) {
        this.finder = finder;
    }

    public void traver(Url url){
        Url tmp = null;
        queue.add(url);
        try {
            while (!queue.isEmpty()){
                tmp = queue.take();
                if(null==tmp){
                    log.info("遍历结束.......");
                    break;
                }
                List<Url> elements = finder.next(tmp);
                if(CollectionUtils.isNotEmpty(elements)){
                    for(Url u : elements){
                        queue.put(u);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("网站抓取结束...");
    }



}
