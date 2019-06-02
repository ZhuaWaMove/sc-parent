package cn.com.thread.wait_notify;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class Target2 implements Runnable{

    private Demo demo;

    public Target2(Demo demo) {
        this.demo = demo;
    }


    @Override
    public void run() {
        demo.setSignal();
    }
}
