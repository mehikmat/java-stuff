package callableNfuture;

import java.util.concurrent.Callable;

/**
 * Created by hdhamee on 2/4/16.
 */
public class SumCallable implements Callable<Long> {

    @Override
    public Long call() throws Exception {
        long sum = 0;
        for (long i = 0; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }
}