package destp.app.crawler.search.finder;

import destp.app.crawler.domain.Element;
import destp.app.crawler.domain.Url;

import java.util.List;

/**
 * 寻找符合规则的url
 */
public abstract class UrlFinder extends BaseFinder {

    public List<Element> next(Url in) {
        return getRule().doRule(in);
    }
}
