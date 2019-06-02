package cn.com.thread.blocking_queue;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class Consumer implements Runnable {



    private BlockingQueueTest demo;

    public Consumer(BlockingQueueTest demo) {
        this.demo = demo;
    }
    @Override
    public void run() {
        while(true){
            try {
                System.out.println("消费队列长度："+demo.size());
                demo.take();
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
