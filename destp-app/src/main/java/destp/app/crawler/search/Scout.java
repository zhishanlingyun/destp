package destp.app.crawler.search;

import com.destp.base.core.AbstractLifePlug;
import destp.app.crawler.api.DuplicateFilter;
import destp.app.crawler.api.duplicate.DuplicateFactory;
import destp.app.crawler.api.duplicate.DuplicateName;
import destp.app.crawler.domain.Element;
import destp.app.crawler.domain.Url;

import java.util.Set;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zsly on 17-11-11.
 * 搜索类,搜取所有的链接路径，存入队列
 */
public class Scout extends AbstractLifePlug {

    //爬虫起始种子集合
    private Url seeds;

    //遍历规则
    private Traverse traverse;

    private Find finder;

    //虑重
    private DuplicateFilter duplicateFilter;

    private BlockingQueue<Element> queue;

    public Scout(Url seeds, BlockingQueue<Element> queue) {
        this.seeds = seeds;
        this.queue = queue;
    }

    public void init(){
        duplicateFilter = DuplicateFactory.getInstance(DuplicateName.MapDuplicate);
        traverse = new Traverse(finder,queue,seeds);
    }

    protected void doStart() {
        traverse.start();
    }

    protected void doStop() {
        traverse.stop();
    }
}
