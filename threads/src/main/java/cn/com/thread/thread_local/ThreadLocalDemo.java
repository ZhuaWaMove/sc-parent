package cn.com.thread.thread_local;

/**
 * Created by GL-shala on 2018/9/16.
 * 使用threadlocal 实现序列
 */
public class ThreadLocalDemo {

    private static ThreadLocal threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return new Integer(0);
        }
    };

    //生成序列的方法
    public void set(ThreadLocal threadLocal){
        Integer count = (Integer) threadLocal.get();
        System.out.println(Thread.currentThread().getName()+" "+count);
        count++;
        threadLocal.set(count);
    }

    public static void main(String[] args) {

        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    threadLocalDemo.set(threadLocal);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    threadLocalDemo.set(threadLocal);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    threadLocalDemo.set(threadLocal);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }


}
