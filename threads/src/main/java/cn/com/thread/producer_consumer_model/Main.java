package cn.com.thread.producer_consumer_model;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class Main {


    public static void main(String[] args) {

        Demo demo = new Demo();

        Producer p = new Producer(demo);
        Consumer c = new Consumer(demo);

        new Thread(p , "p1").start();
        new Thread(p , "p2").start();
        new Thread(p , "p3").start();
        new Thread(p , "p4").start();

        new Thread(c , "c1").start();
        new Thread(c , "c2").start();
        new Thread(c , "c3").start();
        new Thread(c , "c4").start();
    }


}
