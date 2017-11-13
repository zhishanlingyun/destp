package core;

import jodd.util.ThreadUtil;

/**
 * Created by zsly on 17-11-11.
 */
public class Test {

    public static void main(String[] args) {
        App app = new App();
        app.start();
        ThreadUtil.sleep(1000*5);
        app.pause();
        System.out.println("pause");
        ThreadUtil.sleep(1000*5);
        app.restart();
        ThreadUtil.sleep(1000*5);
        app.stop();
    }

}
