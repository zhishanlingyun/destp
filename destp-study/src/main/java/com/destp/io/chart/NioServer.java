package com.destp.io.chart;

import jodd.io.NetUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Administrator on 2017/2/11 0011.
 */
public class NioServer {

    private ServerSocketChannel server;

    private volatile boolean start;


    public NioServer(){
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.socket().bind(new InetSocketAddress("localhost",9090));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        if(!start){
            start = true;
            try {
                while (start){
                    SocketChannel channel = server.accept();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
