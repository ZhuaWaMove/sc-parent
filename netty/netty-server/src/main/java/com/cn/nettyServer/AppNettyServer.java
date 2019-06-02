package com.cn.nettyServer;

import com.cn.nettyServer.server.ServerBootstrap;
import com.cn.nettyServer.server.pool.NioSelectorRunnablePool;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by GL-shala on 2018/5/5.
 */
//@SpringBootApplication
public class AppNettyServer {

    public static void main(String[] args) {


        String str = "{\"timezone\":\"browser\",\"panelId\":2,\"dashboardId\":1,\"range\":{\"from\":\"2019-01-09T10:34:10.177Z\",\"to\":\"2019-01-09T16:34:10.177Z\",\"raw\":{\"from\":\"now-6h\",\"to\":\"now\"}},\"rangeRaw\":{\"from\":\"now-6h\",\"to\":\"now\"},\"interval\":\"20s\",\"intervalMs\":20000,\"targets\":[{\"target\":\"{\\\"a\\\":\\\"true\\\"}\",\"refId\":\"A\",\"type\":\"timeserie\"}],\"maxDataPoints\":960,\"scopedVars\":{\"__interval\":{\"text\":\"20s\",\"value\":\"20s\"},\"__interval_ms\":{\"text\":20000,\"value\":20000}},\"adhocFilters\":[]}";
//        SpringApplication.run(AppNettyServer.class,args);

        //初始化线程
        NioSelectorRunnablePool nioSelectorRunnablePool = new NioSelectorRunnablePool(Executors.newCachedThreadPool(), Executors.newCachedThreadPool());
        //获取服务类
        ServerBootstrap bootstrap = new ServerBootstrap(nioSelectorRunnablePool);
        //绑定端口
        bootstrap.bind(new InetSocketAddress(10101));
        System.out.println("START!!!");
    }

}
