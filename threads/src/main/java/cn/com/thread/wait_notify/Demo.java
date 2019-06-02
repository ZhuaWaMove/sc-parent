package cn.com.thread.wait_notify;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class Demo {

    private volatile int signal;

    public synchronized int getSignal() {
        System.out.println(Thread.currentThread().getName()+"get方法执行了");
        if (signal != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"get方法执行结束了");
        return signal;
    }
    public synchronized void setSignal() {
        signal = 1;
        notifyAll();
        System.out.println("叫醒所有的等待中的线程");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Demo demo = new Demo();
        Target1 t1 = new Target1(demo);
        Target2 t2 = new Target2(demo);


        new Thread(t1).start();
        new Thread(t1).start();
        new Thread(t1).start();
        new Thread(t1).start();


        new Thread(t2).start();
    }
}
