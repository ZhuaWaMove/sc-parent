package cn.com.thread;

/**
 * Created by GL-shala on 2018/6/30.
 */
public class Demo1_Notify {

    public static void main(String[] args) {

        Printer p = new Printer();

        new Thread(){
            public void run(){
                while (true){
                    try {
                        p.print1();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread(){
            public void run(){
                while (true){
                    try {
                        p.print2();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
