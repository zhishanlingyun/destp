package destp.app.crawler.search;

import com.destp.base.core.AbstractAsyLifePlug;
import destp.app.crawler.domain.Element;
import destp.app.crawler.domain.Url;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by zsly on 17-11-11.
 * 遍历策略
 */
public class Traverse extends AbstractAsyLifePlug {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Find finder;

    private Queue<Element> queue;

    private Stack<Url> stack = new Stack<Url>();

    private Url seed;

    public Traverse(Find finder) {
        this.finder = finder;
    }

    public Traverse(Find finder, Queue<Element> queue, Url seed) {
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

    public void setQueue(Queue<Element> queue) {
        this.queue = queue;
    }

    public void setFinder(Find finder) {
        this.finder = finder;
    }

    public void traver(Url url){
        Url tmp = url;
        do{
            if(!finder.hasNext(tmp)){
                url.setLeaf(true);
                log.debug("进入下载队列 {}",tmp);
                queue.add(tmp);
            }else {
                List<Element> elements = finder.next(tmp);
                for(Element element : elements){
                    log.debug("进入待遍历栈 {}",element);
                    stack.push((Url) element);
                }
            }
            if(stack.empty()){
                log.info("遍历结束.......");
                break;
            }else {
                tmp = (Url)stack.pop();
            }
        }while (!stack.empty());
    }



}
