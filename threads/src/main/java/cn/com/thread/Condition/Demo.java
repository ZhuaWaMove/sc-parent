//package cn.com.thread.Condition;
//
///**
// * Created by GL-shala on 2018/9/15.
// */
//public class Demo {
//
//    public int signal = 1;
//
//    public synchronized void a(){
//        if(signal != 1){
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(Thread.currentThread().getName()+" "+s);
//        signal++;
//        notifyAll();
//    }
//    public synchronized void b(){
//        if(signal != 2){
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        signal++;
//        notifyAll();
//    }
//    public synchronized void c(){
//        if (signal !=3){
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        signal=1;
//        notifyAll();
//    }
//
//    public static void main(String[] args) {
//
//        Demo demo = new Demo();
//
//        new Thread(new A(demo)).start();
//        new Thread(new B(demo)).start();
//        new Thread(new C(demo)).start();
//    }
//}
//class A implements Runnable{
//
//    private Demo demo;
//
//    public A(Demo demo) {
//        this.demo = demo;
//    }
//
//    @Override
//    public void run() {
//        demo.c();
//    }
//}
//class B implements Runnable{
//    private Demo demo;
//
//    public B(Demo demo) {
//        this.demo = demo;
//    }
//
//    @Override
//    public void run() {
//        demo.b();
//    }
//}
//class C implements Runnable{
//    private Demo demo;
//
//    public C(Demo demo) {
//        this.demo = demo;
//    }
//    @Override
//    public void run() {
//        demo.a();
//    }
//}