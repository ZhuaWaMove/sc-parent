package cn.com.thread.Condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class DemoD {

    public int signal = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public  void a(){
        lock.lock();
        while(signal != 1){
            try {
                System.out.println(Thread.currentThread().getName()+" 不满足条件等待开始...");
                c1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+" "+signal);
        signal++;
        c2.signal();
        lock.unlock();
    }
    public  void b(){
        lock.lock();
        while(signal != 2){

            try {
                System.out.println(Thread.currentThread().getName()+" 不满足条件等待开始...");
                c2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+" "+signal);
        signal++;
        c3.signal();
        lock.unlock();
    }
    public  void c(){
        lock.lock();
        while (signal !=3){
            try {
                System.out.println(Thread.currentThread().getName()+" 不满足条件等待开始...");
                c3.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+" "+signal);
        signal=1;
        c1.signal();
        lock.unlock();
    }

    public static void main(String[] args) {

        DemoD demo = new DemoD();

        new Thread(new C(demo) , "C").start();
        new Thread(new B(demo) , "B").start();
        new Thread(new A(demo) , "A").start();
    }
}

class A implements Runnable{

    private DemoD demo;

    public A(DemoD demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.a();
    }
}

class B implements Runnable{
    private DemoD demo;

    public B(DemoD demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.b();
    }
}

class C implements Runnable{
    private DemoD demo;

    public C(DemoD demo) {
        this.demo = demo;
    }
    @Override
    public void run() {
        demo.c();
    }
}