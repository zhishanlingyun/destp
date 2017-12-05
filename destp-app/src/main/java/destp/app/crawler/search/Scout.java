package destp.app.crawler.search;

import com.destp.base.core.AbstractLifePlug;
import destp.app.crawler.api.Dispatcher;
import destp.app.crawler.api.DuplicateFilter;
import destp.app.crawler.api.duplicate.DuplicateFactory;
import destp.app.crawler.api.duplicate.DuplicateName;
import destp.app.crawler.domain.Element;
import destp.app.crawler.domain.Url;
import destp.app.crawler.jd.finder.JdFinder;
import destp.app.crawler.process.processer.JdProcesser;

import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
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

    private Find finder = new JdFinder();

    //虑重
    //private DuplicateFilter duplicateFilter;

    private Dispatcher dispatcher;

    private BlockingQueue<Url> queue = new ArrayBlockingQueue<Url>(100);

    public Scout(Url seeds) {
        this.seeds = seeds;
        init();
    }

    public void init(){
        //duplicateFilter = DuplicateFactory.getInstance(DuplicateName.MapDuplicate);
        finder.init();
        dispatcher = new Dispatcher();
        dispatcher.addProcesser(new JdProcesser());
        finder.setDispatcher(dispatcher);
        traverse = new Traverse(finder,queue,seeds);
    }

    protected void doStart() {
        dispatcher.start();
        traverse.start();
    }

    protected void doStop() {
        traverse.stop();
    }
}
