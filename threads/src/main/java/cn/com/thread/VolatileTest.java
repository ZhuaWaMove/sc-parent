package cn.com.thread;

/**
 * Created by GL-shala on 2018/7/10.
 * volatile 变量 自增运算测试
 */
public class VolatileTest {


    private static volatile int race = 0;

    public static void increase(){
        race++;
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
             threads[i] = new Thread(new Runnable() {
                 @Override
                 public void run() {
                     for (int j = 0; j < 10; j++) {
                         increase();
                     }
                 }
             });
            threads[i].start();
        }
        //等待所有累加线程都结束
        while(Thread.activeCount() > 1)
          Thread.yield();
          System.out.println(race);
    }
}
