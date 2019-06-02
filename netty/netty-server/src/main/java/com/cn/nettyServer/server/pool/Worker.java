package com.cn.nettyServer.server.pool;

import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

/**worker接口
 * Created by GL-shala on 2018/5/6.
 */
public interface Worker {

    /**
     * 加入新的客户端会话
     * @param socketChannel
     */
    public void registerNewChannelTask(SocketChannel socketChannel);

}
