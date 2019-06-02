package com.cn.nettyServer.server.pool;

import java.nio.channels.ServerSocketChannel;

/**
 * Created by GL-shala on 2018/5/6.
 */
public interface Boss {

    public void regiesterAcceptChannelTask(ServerSocketChannel serverChannel);

}
