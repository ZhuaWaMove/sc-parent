package cn.com.thread.synchronizer;

/**
 * Created by GL-shala on 2018/7/5.
 */
public class SynchronizerMain {



    public static void main(String[] args) {
        final SynchronizerTest st = new SynchronizerTest();

        new Thread(
                new Runnable(){

                    public void run(){
                        st.sync1();
                    }
                }
        ).start();

        new Thread(
                new Runnable(){

                    public void run(){
                        st.sync2();
                    }
                }
        ).start();
    }
}
