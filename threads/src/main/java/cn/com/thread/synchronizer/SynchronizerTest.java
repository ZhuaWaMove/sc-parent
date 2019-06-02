package cn.com.thread.synchronizer;

/**
 * Created by GL-shala on 2018/7/5.
 */
public  class SynchronizerTest {

    public synchronized void sync1(){

        System.out.println("sync1 start...");

        try{
//            synchronized (this){

                System.out.println("sync1 running...");
                Thread.sleep(3000);
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("sync1 stop...");
    }

    public synchronized void sync2(){

        System.out.println("sync2 start...");

        try{
//            synchronized (this){

                System.out.println("sync2 running...");
                Thread.sleep(1000);
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("sync2 stop...");
    }
}
