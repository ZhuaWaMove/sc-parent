package cn.com.thread.blocking_queue;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class Demo {

    public int count;
    public static final int maxCount = 20;
    public synchronized void get(){
        while(count <= 0 ){
            try {
                System.out.println(Thread.currentThread().getName()+"库存数量达到下限，消费者等待...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        notifyAll();
        System.out.println(Thread.currentThread().getName()+"当前库存"+count);
    }

    public synchronized void set(){
        while(count >= maxCount){
            try {
                System.out.println(Thread.currentThread().getName()+"库存数量达到上限，生产者等待...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        notifyAll();
        System.out.println(Thread.currentThread().getName()+"生产者生产库存"+count);
    }



}
