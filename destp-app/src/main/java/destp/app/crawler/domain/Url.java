package destp.app.crawler.domain;

/**
 * Created by zsly on 17-11-11.
 */
public class Url implements Element{

    private String url;

    private boolean isLeaf;

    public Url(String url, boolean isLeaf) {
        this.url = url;
        this.isLeaf = isLeaf;
    }

    public Url() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
