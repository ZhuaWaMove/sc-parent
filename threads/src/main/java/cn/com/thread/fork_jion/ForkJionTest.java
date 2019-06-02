package cn.com.thread.fork_jion;

import java.util.concurrent.*;

/**
 * Created by GL-shala on 2018/9/25.
 */
public class ForkJionTest extends RecursiveTask{


    private static final int THRESHOLD = 2;
    private int start;
    private int end;

    public ForkJionTest(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Object compute() {
        System.out.println(Thread.currentThread().getName());
        int sum = 0;
        //任务足够小进行任务计算
        boolean canCompute = (end -start) <= THRESHOLD;
        if(canCompute){
            for (int i = start ; i <= end; i++){
                sum += i;
            }
        }else{

            //如果任务大于阈值就进行任务拆分
            int middle = (start+end)/2;

            ForkJionTest forkJion1 = new ForkJionTest(start, middle);
            ForkJionTest forkJion2 = new ForkJionTest(middle + 1, end);

            //执行子任务
            forkJion1.fork();
            forkJion2.fork();

            //子任务执行完之后进行合并
            int result1 = (int) forkJion1.join();
            int result2 = (int) forkJion2.join();

            //合并结果
            sum = result1 + result2;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool(1000);
        long starttime = System.currentTimeMillis();
        Future future =  forkJoinPool.submit(new ForkJionTest(1,10000000));
        System.out.println("======================");
        System.out.println("sum: "+future.get());
        long endtime = System.currentTimeMillis();
        System.out.println("耗时："+ (endtime-starttime));

        long starttime1 = System.currentTimeMillis();
        int sum1 = 0;
        for (int i = 0; i <= 10000000;i++){
            sum1 +=i;
        }
        System.out.println("sum1: "+sum1);
        long endtime1 = System.currentTimeMillis();
        System.out.println("耗时："+ (endtime1-starttime1));

    }
}
