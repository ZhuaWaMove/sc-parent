package cn.com.thread.customer_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by GL-shala on 2018/9/15.
 */
public class MyLock implements Lock{

    class Helper extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            //如果第一个线程进来 可以获取到锁  返回true

            //如果第二个线程进来 拿不到锁 就返回false,有种特例 如果当前进来的线程和当前保存的线程是同一个线程，则可以拿到锁，但有代价要更新状态值。

            //如何判断是第一个线程 还是第二个线程进来了？
            int state = getState();
            if(Thread.currentThread() == getExclusiveOwnerThread()){
                compareAndSetState(1 , state+arg);
                setExclusiveOwnerThread(null);
                return false;
            }
            if( state == 0 ){
                if(compareAndSetState(0 ,  arg)){
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }

            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {

            //获取所和释放锁 是成对出现的 调用此方法的线程一定是同一个线程

            if(Thread.currentThread() != getExclusiveOwnerThread()){
                throw new RuntimeException();
            }

            int state = getState() - arg;
            boolean flag = false;
            if(state == 0){
                setExclusiveOwnerThread(null);
                flag = true;
            }
            setState(state);

            return flag;
        }

        // Provide a Condition
        Condition newCondition() { return new ConditionObject(); }
    }

    Helper helper = new Helper();

    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1 , unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newCondition();
    }





}
