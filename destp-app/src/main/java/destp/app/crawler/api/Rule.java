package destp.app.crawler.api;

import destp.app.crawler.domain.Element;
import destp.app.crawler.domain.Url;

import java.util.List;

/**
 * 匹配规则
 */
public interface Rule {

    public List<Element> doRule(Url url);

}
