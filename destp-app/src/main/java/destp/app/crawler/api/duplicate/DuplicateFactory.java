package destp.app.crawler.api.duplicate;

import destp.app.crawler.api.DuplicateFilter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateFactory {

    private static Map<String,DuplicateFilter> filterMap = new HashMap<String, DuplicateFilter>();

    public static DuplicateFilter getInstance(String type){
        DuplicateFilter duplicateFilter = filterMap.get(type);
        Assert.notNull(duplicateFilter,"虑重器为空");
        return duplicateFilter;
    }

    public void init(List<DuplicateFilter> filters){
        if(CollectionUtils.isEmpty(filters)){
            MapDuplicateFilter mapDuplicateFilter = new MapDuplicateFilter();
            filterMap.put(mapDuplicateFilter.getName(),mapDuplicateFilter);
        }else {
            for(DuplicateFilter filter : filters){
                filterMap.put(filter.getName(),filter);
            }
        }
    }

}
