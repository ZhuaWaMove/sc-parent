package cn.com.thread.read_write_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class Demo {

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();

    private static Map map = new HashMap<>();
    public Object get(String key){
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+"读操作正在进行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return map.get(key);
        }finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName()+"读操作执行完了");
        }

    }
    public void put(String key , Object value){
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName()+"写操作正在进行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key , value);
        }finally {
            writeLock.unlock();
            System.out.println(Thread.currentThread().getName()+"写操作执行完毕");
        }

    }

    public static void main(String[] args) {

        Demo demo = new Demo();
         //两个读操作同时进行

//        map.put("key1","value1");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Object value = demo.get("key1");
//                System.out.println(Thread.currentThread().getName()+"读取到的值"+value);
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Object value = demo.get("key1");
//                System.out.println(Thread.currentThread().getName()+"读取到的值"+value);
//            }
//        }).start();
//        //两个写操作
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.put("key1","value1");
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.put("key2","value2");
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.put("key3","value3");
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.put("key4","value4");
//            }
//        }).start();

        //读写操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.put("key1","value1");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" "+demo.get("key1"));
            }
        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                demo.put("key3","value3");
//            }
//        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName()+" "+demo.get("key3"));
//            }
//        }).start();
    }

}
