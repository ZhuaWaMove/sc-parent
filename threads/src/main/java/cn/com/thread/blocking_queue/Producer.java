package cn.com.thread.blocking_queue;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class Producer implements Runnable{

    private BlockingQueueTest demo;

    public Producer(BlockingQueueTest demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("生产队列长度："+demo.size());
                demo.put(2);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
