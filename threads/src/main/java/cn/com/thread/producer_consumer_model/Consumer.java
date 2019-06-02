package cn.com.thread.producer_consumer_model;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class Consumer implements Runnable {



    private Demo demo;

    public Consumer(Demo demo) {
        this.demo = demo;
    }
    @Override
    public void run() {
        while(true){
            demo.get();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
