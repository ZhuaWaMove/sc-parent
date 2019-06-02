package cn.com.thread.wait_notify;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class Target1 implements Runnable{


   private Demo demo;

   public Target1(Demo demo) {
      this.demo = demo;
   }

   @Override
   public void run() {
      System.out.println(Thread.currentThread().getName()+" "+demo.getSignal());
   }
}
