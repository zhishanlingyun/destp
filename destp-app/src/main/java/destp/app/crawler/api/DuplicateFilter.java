package destp.app.crawler.api;

import destp.app.crawler.domain.Element;

public interface DuplicateFilter {

    public String getName();

    public void config();

    public boolean filter(Element element);

}
