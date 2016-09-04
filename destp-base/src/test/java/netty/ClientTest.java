package netty;

import com.destp.base.net.tcp.netty.EchoClient;

/**
 * Created by Administrator on 2016/7/30 0030.
 */
public class ClientTest {

    public static void main(String[] args){
        EchoClient client = new EchoClient();
        client.conn();
    }
}
