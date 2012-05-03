package fibonacci;

import java.util.concurrent.*;

/**
 * @author SanderMak
 */
public class FibExecutorSvc {
    
    private static ExecutorService xsvc = Executors.newCachedThreadPool();
   
    public static int fib(final int n) throws InterruptedException, ExecutionException {
        if (n < 2) {
            return n;
        } else {

            Callable<Integer> fib1 = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    return fib(n - 1);
                }
            };
            
            Callable<Integer> fib2 = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    return fib(n - 2);
                }
            };

            final Future<Integer> fib1fut = xsvc.submit(fib1);
            final Future<Integer> fib2fut = xsvc.submit(fib2);
            
            return fib1fut.get() + fib2fut.get();
        }
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        System.out.println(fib(7));
        long end = System.currentTimeMillis();
        
        System.out.println("Elapsed: " + (end - start));
        System.out.println("Pooled threads: " + 
                ((ThreadPoolExecutor)xsvc).getLargestPoolSize());
        xsvc.shutdownNow();
    }
}
