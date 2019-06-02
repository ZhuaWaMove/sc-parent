package cn.com.thread.thread_lock_reentry;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class LockReentry {


    public  void a(){
        lock.lock();
         b();
        lock.unlock();
        System.out.println("a 执行了");
    }

    public  void b(){
        System.out.println("b 执行了");
    }

    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) {

        LockReentry lockReentry = new LockReentry();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    lockReentry.a();
                    lock.unlock();
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
