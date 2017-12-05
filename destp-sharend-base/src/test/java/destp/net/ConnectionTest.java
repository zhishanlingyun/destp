package destp.net;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionTest {

    public static void conn() throws Exception{
        ExecutorService exe = Executors.newFixedThreadPool(50);
        File hostsFile = new File("E:\\test\\hosts.txt.txt");
        List<String> ips  = FileUtils.readLines(hostsFile,"UTF-8");
        ArrayList<String> arrayList = new ArrayList<String>();


        for(String host : ips){
            if(StringUtils.isEmpty(host.trim())||host.charAt(0)=='#'){
                continue;
            }
            arrayList.add(host);
        }
        System.out.println("---------size is "+arrayList.size());
        final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(arrayList.size(),true,arrayList);
        System.out.println(queue.size());
        final CountDownLatch latch = new CountDownLatch(queue.size());
        for(int i=0;i<50;i++){
            exe.submit(new Runnable() {
                public void run() {

                        while (true){
                            try {
                            String hosts = queue.poll();
                            if(StringUtils.isEmpty(hosts)){
                                break;
                            }
                            String[] param = hosts.split("\\s");
                            String ip = param[0];
                            String hostname = param[1];
                            Socket socket = new Socket();
                            socket.connect(new InetSocketAddress(ip, 80), 100);
                            if(socket.isConnected()){
                                System.out.println(ip+" "+hostname+" ok");
                            }} catch (Exception e) {
                            //System.out.println(ip+" "+hostname+" 超时");
                        }finally {
                                latch.countDown();
                        }

                        }


                }
            });
        }
        latch.await();
        System.out.println("---------end----------");
        exe.shutdownNow();

        //Socket socket = new Socket();
    }

    public static void main(String[] args) {
        try {
            conn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
