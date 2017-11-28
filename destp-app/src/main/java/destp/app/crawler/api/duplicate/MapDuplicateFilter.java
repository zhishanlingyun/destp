package destp.app.crawler.api.duplicate;

import destp.app.crawler.api.DuplicateFilter;
import destp.app.crawler.domain.Element;

import java.util.concurrent.ConcurrentHashMap;

public class MapDuplicateFilter implements DuplicateFilter {

    private ConcurrentHashMap<Element,Integer> map = new ConcurrentHashMap<Element, Integer>(100);

    public String getName() {
        return DuplicateName.MapDuplicate;
    }

    public void config() {

    }

    public boolean filter(Element element) {
        Integer v = map.putIfAbsent(element,1);
        if(null!=v){
            return true;
        }
        return false;
    }
}
