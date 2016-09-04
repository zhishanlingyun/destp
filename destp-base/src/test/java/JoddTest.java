import jodd.Jodd;
import jodd.core.JoddCore;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/7/23 0023.
 */
public class JoddTest {

    public static void main(String[] args){

        //System.out.println(Jodd.getModule(1));
        new Thread(new Runnable() {
            public void run() {
                ExecutorService exe = Executors.newFixedThreadPool(2);
                exe.execute(new Runnable() {
                    public void run() {
                        System.out.println("hello world");
                    }
                });
                //exe.shutdown();

            }
        }).start();


    }
}
