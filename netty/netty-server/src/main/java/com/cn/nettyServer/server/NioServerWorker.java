package com.cn.nettyServer.server;

import com.cn.nettyServer.server.pool.NioSelectorRunnablePool;
import com.cn.nettyServer.server.pool.Worker;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executor;

/**
 * Created by GL-shala on 2018/5/6.
 */
public class NioServerWorker extends AbstractNioSelector implements Worker {

    public NioServerWorker(Executor executor, String threadName, NioSelectorRunnablePool selectorRunnablePool) {
        super(executor, threadName, selectorRunnablePool);
    }

    @Override
    public void registerNewChannelTask(SocketChannel socketChannel) {

    }

    @Override
    protected int select(Selector selector) throws IOException {
        return 0;
    }

    @Override
    protected void process(Selector selector) throws IOException {

    }
}
