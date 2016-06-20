package libraries;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by hdhamee on 6/16/16.
 */
public class Java8ParallelArrays {
    /**
     * Java 8 release adds a lot of new methods to allow parallel arrays processing.
     * Arguably, the most important one is parallelSort() which may significantly speedup the
     * sorting on multicore machines.
     *
     */
    public static void main(String[] args) {
        long[] arrayOfLong = new long [ 20000 ];

        Arrays.parallelSetAll(arrayOfLong,index -> ThreadLocalRandom.current().nextInt(1000000));
        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );

        System.out.println();
        Arrays.parallelSort( arrayOfLong );
        Arrays.stream( arrayOfLong ).limit( 10 ).forEach(
                i -> System.out.print( i + " " ) );

        System.out.println();
    }
}