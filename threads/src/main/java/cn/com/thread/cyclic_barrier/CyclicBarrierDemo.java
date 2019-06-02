package cn.com.thread.cyclic_barrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by GL-shala on 2018/9/16.
 */
public class CyclicBarrierDemo {


    public void meeting(CyclicBarrier cyclicBarrier){

        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(4000));
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName()+"到达会议开始等待开会...");
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {

        }
        System.out.println(Thread.currentThread().getName()+"开始发言...");

    }

    public static void main(String[] args) {

        CyclicBarrierDemo cyclicBarrierDemo = new CyclicBarrierDemo();
        CyclicBarrier cyclic = new CyclicBarrier(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("人员到齐开始开会...");
            }
        });

        for (int i = 0; i < 10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    cyclicBarrierDemo.meeting(cyclic);
                }
            }).start();
        }

    }
}

