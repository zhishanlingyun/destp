package com.destp.base.net.tcp.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.Logger;

import java.net.SocketAddress;

/**
 * Created by Administrator on 2016/7/30 0030.
 */
public class EchoClient {

    private static final Logger log = Logger.getLogger(EchoClient.class);

    private EventLoopGroup workerGroup;

    private String host = "localhost";

    private int port = 8989;

    public void conn(){
        workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new SimpleClient());
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect(host,port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void disconn(){
        workerGroup.shutdownGracefully();
    }

    class SimpleClient extends ChannelInboundHandlerAdapter{
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            final ByteBuf time = ctx.alloc().buffer(4); // (2)
            time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));
            ctx.writeAndFlush(time);
        }
    }

}
