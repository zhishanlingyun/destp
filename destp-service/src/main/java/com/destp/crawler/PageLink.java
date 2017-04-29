package com.destp.crawler;

/**
 * Created by Administrator on 2016/10/4 0004.
 */
public class PageLink {

    private String linkName;
    private String link;

    public PageLink(String linkName, String link) {
        this.linkName = linkName;
        this.link = link;
    }

    public PageLink() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageLink pageLink = (PageLink) o;

        if (linkName != null ? !linkName.equals(pageLink.linkName) : pageLink.linkName != null) return false;
        return !(link != null ? !link.equals(pageLink.link) : pageLink.link != null);

    }

    @Override
    public int hashCode() {
        int result = linkName != null ? linkName.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }
}
