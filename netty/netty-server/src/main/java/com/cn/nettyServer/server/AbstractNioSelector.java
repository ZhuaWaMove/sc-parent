package com.cn.nettyServer.server;

import com.cn.nettyServer.server.pool.NioSelectorRunnablePool;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

import static javafx.beans.binding.Bindings.select;

/**抽象的selector类
 * Created by GL-shala on 2018/5/6.
 */
public abstract class AbstractNioSelector implements Runnable{

    /**
     * 线程池
     */
    private final Executor executor;

    /**
     * 选择器
     */
    protected Selector selector;

    /**
     * 选择器wakenUp状态标记
     */
    protected final AtomicBoolean wakenUp = new AtomicBoolean();

    /**
     * 任务队列
     */
    private final Queue<Runnable> taskQueue = new ConcurrentLinkedDeque<>();

    /**
     * 线程名称
     */
    private String threadName;

    /**
     * 线程管理对象
     */
    protected NioSelectorRunnablePool selectorRunnablePool;


    protected AbstractNioSelector(Executor executor, String threadName, NioSelectorRunnablePool selectorRunnablePool) {
        this.executor = executor;
        this.threadName = threadName;
        this.selectorRunnablePool = selectorRunnablePool;
        openSelector();
    }

    /**
     * 获取selector并启动线程
     */
    private void openSelector() {

        try {
            this.selector = Selector.open();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create a selector.");
        }
        executor.execute(this);
    }

    @Override
    public void run() {

        Thread.currentThread().setName(this.threadName);
        while (true){
            try {
                wakenUp.set(false);
                select(selector);
                processTaskQueue();
                process(selector);
            }catch (Exception e){
                //ignore
            }
        }
    }

    /**
     * 执行队列里的任务
     */
    private void processTaskQueue() {

        for (;;){
            final Runnable task = taskQueue.poll();
            if(task == null){
                break;
            }
            task.run();
        }
    }

    /**
     * 获取线程管理器对象
     * @return
     */
    public NioSelectorRunnablePool getSelectorRunnablePool(){
        return selectorRunnablePool;
    }

    /**
     * select抽象方法
     * @param selector
     * @return
     */
    protected abstract int select(Selector selector) throws IOException;

    /**
     * selector的业务处理
     * @param selector
     */
    protected abstract void process(Selector selector) throws IOException;

    /**
     * 注册一个任务并激活selector
     * @param task
     */
    protected final void registerTask(Runnable task){
        taskQueue.add(task);

        Selector selector = this.selector;

        if(selector != null){
            if(wakenUp.compareAndSet(false, true)){
                selector.wakeup();
            }
        }else{
            taskQueue.remove(task);
        }
    }

}
