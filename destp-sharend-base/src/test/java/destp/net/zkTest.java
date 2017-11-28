package destp.net;

import jodd.util.ThreadUtil;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class zkTest {

    private static final String zkAddress = "fd.server:2181";

    //连接,增删改查
    public void test1(){
        String path = "/zk-test-root";
        CountDownLatch latch = new CountDownLatch(1);
        AsyncCallback.Children2Callback callback = new AsyncCallback.Children2Callback() {

            public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
                StringBuilder sb = new StringBuilder(50);
                sb.append("rc is ")
                        .append(rc).append("\n")
                        .append("path is ")
                        .append(path).append("\n")
                        .append("ctx is ")
                        .append(ctx).append("\n")
                        .append("children is ")
                        .append(Arrays.toString(children.toArray())).append("\n")
                        .append("stat is ")
                        .append(stat);
                System.out.println(sb.toString());

            }
        };
        try {
            ZooKeeper zk = null;
            zk = new ZooKeeper(zkAddress,1000,new MoinitWatcher("conn-watcher",zk));
            System.out.println(zk.getState());
            //该方法是同步方法
            List<String> pathData = zk.getChildren(path,true);
            System.out.println(Arrays.toString(pathData.toArray()));
            //while (true){
            zk.getChildren(path, true,callback ,null);
            //ThreadUtil.sleep(5000);
           // }

            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void zk(){
        CountDownLatch latch = new CountDownLatch(1);
        ZkClient zkClient = new ZkClient(zkAddress);
        //zkClient.connect(1000,new MoinitWatcher("moinit-watchar",null));
        zkClient.subscribeChildChanges("/zk-test-root", new IZkChildListener() {
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                StringBuilder sb = new StringBuilder();
                sb.append("parentpath is ").append(parentPath).append("\n")
                        .append("childs is ").append(Arrays.toString(currentChilds.toArray()));
                System.out.println(sb);
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void zk1(){
        Client client = new Client(zkAddress+"/zk-test-root");
        client.getChildern();
    }


    public static void main(String[] args) {
        //new zkTest().test1();
        //new zkTest().zk();
        new zkTest().zk1();
    }

    class MoinitWatcher implements Watcher{
        private String name;
        private ZooKeeper zk;
        private Thread zkThread;

        public MoinitWatcher(String name,ZooKeeper zk) {
            this.name = name;
            this.zk = zk;
        }
        public void process(WatchedEvent event) {

            zkThread = Thread.currentThread();
            System.out.println(zkThread);
            System.out.println("[ "+name+" ] result is "+event);
            //ThreadUtil.sleep(1000);
            //zk.register(this);
            //zk.getData("/zk-test-root",this,null,null);
            //zk.register(this);

        }
    }

    class Client implements Watcher{
        private ZooKeeper zk;
        private String address;
        private Thread zkThread;
        //private CountDownLatch latch = new CountDownLatch(1);
        private Lock lock = new ReentrantLock();
        private Condition connStat = lock.newCondition();
        private Condition changeStat = lock.newCondition();

        public Client(String address) {
            this.address = address;
            try {
                zk = new ZooKeeper(this.address,30000,this);
                lock.lockInterruptibly();
                try {
                    System.out.println("conn wait");
                    connStat.await();
                    System.out.println("conn release");
                } finally {
                    lock.unlock();
                }
                System.out.println("zk start");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void getChildern(){
            try {
                while(true){
                    List<String> children = zk.getChildren("/", true);
                    System.out.println(Arrays.toString(children.toArray()));
                    lock.lock();
                    try {
                        System.out.println("getchildren wait");
                        changeStat.await();
                        System.out.println("getchildren release");
                    } finally {
                        lock.unlock();
                    }
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void conn(Watcher watcher){
            zk.register(watcher);
        }

        public void process(WatchedEvent event) {
            //if(Event.KeeperState.SyncConnected)
           if(event.getState().equals(Event.KeeperState.SyncConnected)&&event.getPath()==null){
               try {
                   lock.lockInterruptibly();
                   try {
                       connStat.signalAll();
                   } finally {
                       lock.unlock();
                   }
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           if(event.getType().equals(Event.EventType.NodeChildrenChanged)){
               try {
                   lock.lockInterruptibly();
                   changeStat.signalAll();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               } finally {
                   lock.unlock();
               }
           }
            zkThread = Thread.currentThread();
            System.out.println(zkThread);
            System.out.println(event);

            //}
        }
    }


}
