package cn.com.thread.customer_lock;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class MyLockTest {

    private int count = 0;

    public void a(){

        System.out.println("a 执行了"+ count++);


    }
    public void b(){



        System.out.println("b 执行了"+count++);

    }

    MyLock lock = new MyLock();
    MyLock lock2 = new MyLock();
    public int next(){
        try {
            lock.lock();
            Thread.sleep(100);
            int reslut = count++;
            return reslut;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        return 0;
    }
    public static void main(String[] args) {
        MyLockTest myLockTest = new MyLockTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true)
//                    System.out.println(Thread.currentThread().getId()+" "+myLockTest.next());
                myLockTest.lock.lock();
                myLockTest.a();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myLockTest.lock2.lock();

                myLockTest.lock2.unlock();

                myLockTest.lock.unlock();
            }
        },"线程A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true)
//                    System.out.println(Thread.currentThread().getId()+ " "+ myLockTest.next());


                myLockTest.lock2.lock();
                myLockTest.b();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myLockTest.lock.lock();

                myLockTest.lock.unlock();

                myLockTest.lock2.unlock();

            }
        },"线程B").start();
    }
}
