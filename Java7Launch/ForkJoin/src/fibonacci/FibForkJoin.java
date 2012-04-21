package fibonacci;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibForkJoin extends RecursiveTask<Long> {
    private final long fibValue;

    public FibForkJoin(long fibValue) {
        this.fibValue = fibValue;
    }

    @Override
    public Long compute() {
        if (fibValue < 2L) {
            return fibValue;
        } else {
            FibForkJoin firstWorker = new FibForkJoin(fibValue - 1L);
            firstWorker.fork();
            
            FibForkJoin secondWorker = new FibForkJoin(fibValue - 2L);
            secondWorker.fork();
            
            return  secondWorker.join() + firstWorker.join();
        }
    }
   
    public static void main(String[] args) {
        ForkJoinPool fjPool = new ForkJoinPool();
        // Fib(42) should take about 17 seconds
        System.out.println(fjPool.invoke(new FibForkJoin(4)));
        System.out.println("Stolen tasks: " + fjPool.getStealCount());
    }
}
