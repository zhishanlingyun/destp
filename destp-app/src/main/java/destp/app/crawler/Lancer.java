package destp.app.crawler;

import destp.app.crawler.domain.Url;
import destp.app.crawler.search.Scout;

public class Lancer {

    public static void main(String[] args) {
        Url url = new Url("http://source.jd.com/app/pms",false);
        Scout scout = new Scout(url);
        scout.start();
    }

}
