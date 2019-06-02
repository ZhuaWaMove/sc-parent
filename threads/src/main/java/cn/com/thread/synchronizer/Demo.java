package cn.com.thread.synchronizer;

/**
 * Created by GL-shala on 2018/9/13.
 */
public class Demo {

    Object obj1 = new Object();
    Object obj2 = new Object();

    public void a() {
        synchronized (obj1){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (obj2){

                System.out.println("a()");
            }
        }
    }

    public void b()  {
        synchronized (obj2){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj1){

                System.out.println("b()");
            }
        }
    }

    public static void main(String[] args) {

        Demo d = new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.a();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                d.b();
            }
        }).start();
    }
}
