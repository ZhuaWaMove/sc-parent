package cn.com.thread.synchronizer;

/**
 * Created by GL-shala on 2018/7/12.
 */
public class Singleton {
    //volatile 可以防止java虚拟机进行指令重排序（底层对执行的优化），避免线程安全。
    private volatile static Singleton singleton;

    public static Singleton getSingleton(){
        if(singleton == null){
          synchronized (Singleton.class){
              if(singleton == null){
                  singleton = new Singleton();
              }
          }
        }
        return singleton;
    }


    public static void main(String[] args) {
        Singleton.getSingleton();
    }
}
