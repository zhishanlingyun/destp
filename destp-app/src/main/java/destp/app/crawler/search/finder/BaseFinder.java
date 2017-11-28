package destp.app.crawler.search.finder;

import destp.app.crawler.api.Rule;
import destp.app.crawler.search.Find;

public abstract class BaseFinder implements Find {

    private Rule rule;

    public BaseFinder(Rule rule) {
        this.rule = rule;
    }

    public BaseFinder() {}

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
