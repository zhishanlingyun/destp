package com.destp.crawler;

import java.util.List;

/**
 * Created by Administrator on 2016/10/4 0004.
 */
public interface RuleCrawler {

    public int getDepth();

    public List<PageLink> getPageLinkList(String host);

    public String getNextHost(String host);

}
