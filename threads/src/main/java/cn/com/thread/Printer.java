package cn.com.thread;

/**
 * Created by GL-shala on 2018/6/30.
 */
public class Printer {

    private int flag = 1;

    public void print1() throws InterruptedException {
        synchronized (this){
            while(flag != 1){
                this.wait();
            }
            System.out.print("a");
            System.out.print("b");
            System.out.print("c");
            System.out.print("d");
            System.out.print("e");
            System.out.println();
            flag = 0;
            this.notifyAll();
        }
    }
    public void print2() throws InterruptedException {
        synchronized (this){
            while(flag != 0){
                this.wait();
            }
            System.out.print("1");
            System.out.print("2");
            System.out.print("3");
            System.out.print("4");
            System.out.print("5");
            System.out.println();
            flag = 1;
            this.notifyAll();
        }
    }
}
