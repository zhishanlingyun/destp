package netty;

import com.destp.base.net.tcp.netty.EchoServer;

/**
 * Created by Administrator on 2016/7/30 0030.
 */
public class ServerTest {

    public static void main(String[] args){
        EchoServer server = new EchoServer();
        server.start();
    }
}
