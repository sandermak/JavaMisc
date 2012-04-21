package fibonacci;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author SanderMak
 */
public class FibThreads {

    private static AtomicLong cnt = new AtomicLong(0);
   
    public static int fib(final int n) throws InterruptedException {
        if (n < 2) {
            return n;
        } else {
            final int[] t1 = {0}, t2 = {0};

            Thread thread1 = new Thread() {
                {cnt.incrementAndGet();}
                public void run() {
                    try {
                        t1[0] = fib(n - 1);
                    } catch (InterruptedException ex) {
                    }
                }
            };

            Thread thread2 = new Thread() {
                {cnt.incrementAndGet();}
                public void run() {
                    try {
                        t2[0] = fib(n - 2);
                    } catch (InterruptedException ex) { }
                }
            };

            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();

            return t1[0] + t2[0];
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println(fib(2));
        System.out.println("Number of threads: " + cnt);
    }
}
