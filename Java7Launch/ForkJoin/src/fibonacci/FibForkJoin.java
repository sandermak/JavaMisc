package fibonacci;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibForkJoin extends RecursiveTask<Integer> {
    private final int fibValue;

    public FibForkJoin(int fibValue) {
        this.fibValue = fibValue;
    }

    @Override
    public Integer compute() {
        if (fibValue < 2) {
            return fibValue;
        } else {
            FibForkJoin firstWorker = new FibForkJoin(fibValue - 1);
            firstWorker.fork();
            
            FibForkJoin secondWorker = new FibForkJoin(fibValue - 2);
            secondWorker.fork();
            
            return  secondWorker.join() + firstWorker.join();
        }
    }
   
    public static void main(String[] args) {
        ForkJoinPool fjPool = new ForkJoinPool();
        // Fib(42) should take about 17 seconds
        long start = System.currentTimeMillis();
        System.out.println(fjPool.invoke(new FibForkJoin(4)));
        long end = System.currentTimeMillis();
        
        System.out.println("Elapsed: " + (end - start));
        System.out.println("Threads: " + fjPool.getPoolSize());
    }
}
