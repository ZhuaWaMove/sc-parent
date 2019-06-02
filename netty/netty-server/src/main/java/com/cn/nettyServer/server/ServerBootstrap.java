package com.cn.nettyServer.server;

import com.cn.nettyServer.server.pool.Boss;
import com.cn.nettyServer.server.pool.NioSelectorRunnablePool;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by GL-shala on 2018/5/6.
 */
public class ServerBootstrap {

    private NioSelectorRunnablePool selectorRunnablePool;

    public ServerBootstrap(NioSelectorRunnablePool selectorRunnablePool) {
        this.selectorRunnablePool = selectorRunnablePool;
    }

    public void bind(final SocketAddress localAddress){

        try {
            //获得一个serverChannel通道
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            //设置通道为非阻塞
            serverChannel.configureBlocking(false);
            //绑定端口
            serverChannel.socket().bind(localAddress);
            //获得一个boss线程
            Boss nextBoss = selectorRunnablePool.nextBoss();
            //向boss注册一个ServerSocket通道
            nextBoss.regiesterAcceptChannelTask(serverChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
