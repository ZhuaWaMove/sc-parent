package cn.com.thread.producer_consumer_model;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class Producer implements Runnable{

    private Demo demo;

    public Producer(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while(true) {

            demo.set();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
