package forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * @author Hikmat Dhamee
 */
public class Main {
    public static void main(String[] args) {
        int nThreads = Runtime.getRuntime().availableProcessors();
        System.out.println("=================================\n No of Processors Available: " + nThreads + "\n");

        // create a random data set
        final int[] data = new int[1000];
        final Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(100);
        }

        // submit the task to the pool
        final ForkJoinPool pool = new ForkJoinPool(4);
        final MaximumFinder finder = new MaximumFinder(data);
        System.out.println(pool.invoke(finder));
    }
}
