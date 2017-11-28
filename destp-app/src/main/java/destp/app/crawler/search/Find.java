package destp.app.crawler.search;

import destp.app.crawler.domain.Element;
import destp.app.crawler.domain.Url;

import java.util.List;

/**
 * Created by zsly on 17-11-11.
 * 寻找策略
 */
public interface Find {

    public boolean hasNext(Url url);

    public List<Element> next(Url in);

}
