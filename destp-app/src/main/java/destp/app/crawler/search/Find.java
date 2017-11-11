package destp.app.crawler.search;

import destp.app.crawler.domain.Url;

/**
 * Created by zsly on 17-11-11.
 * 寻找策略
 */
public interface Find {

    public Url next(Url in);

}
