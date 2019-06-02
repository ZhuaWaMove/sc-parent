package com.cn.nettyServer.server.pool;

import com.cn.nettyServer.server.AbstractNioSelector;
import com.cn.nettyServer.server.NioServerBoss;
import com.cn.nettyServer.server.NioServerWorker;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**select
 * Created by GL-shala on 2018/5/6.
 */
public class NioSelectorRunnablePool{

    //boss线程组
    private final AtomicInteger bossIndex = new AtomicInteger();
    private Boss[] bosses;

    /**
     *线程数组worker
     */
    private final AtomicInteger workerIndex = new AtomicInteger();
    private Worker[] workeres;

    public NioSelectorRunnablePool(Executor boss, Executor worker) {
        initBoss(boss, 1);
        initWorker(worker, Runtime.getRuntime().availableProcessors() * 2);
    }
    /**
     * 初始化boss线程
     * @param boss
     * @param count
     */
    private void initBoss(Executor boss, int count) {
        this.bosses = new NioServerBoss[count];
        for (int i = 0; i < bosses.length; i++) {
            bosses[i] = new NioServerBoss(boss, "boss thread " + (i+1), this);
        }

    }

    /**
     * 初始化worker线程
     * @param worker
     * @param count
     */
    private void initWorker(Executor worker, int count) {
        this.workeres = new NioServerWorker[count];
        for (int i = 0; i < workeres.length; i++) {
            workeres[i] = new NioServerWorker(worker, "worker thread " + (i+1), this);
        }
    }
    //获得一个boss
    public Boss nextBoss() {
        return bosses[Math.abs(bossIndex.getAndIncrement() % bosses.length)];
    }
    //获得一个worker
    public Worker nextWorker() {
        return workeres[Math.abs(workerIndex.getAndIncrement() % workeres.length)];
    }
}
