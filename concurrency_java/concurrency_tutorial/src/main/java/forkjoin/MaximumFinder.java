package forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author Hikmat Dhamee
 */
public class MaximumFinder extends RecursiveTask<Integer> {

    private static final int SEQUENTIAL_THRESHOLD = 5;

    private final int[] data;
    private final int start;
    private final int end;

    public MaximumFinder(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    public MaximumFinder(int[] data) {
        this(data, 0, data.length);
    }

    @Override
    protected Integer compute() {
        final int length = end - start;
        if (length < SEQUENTIAL_THRESHOLD) {
            return computeDirectly();
        }

        final int split = length / 2;
        final MaximumFinder left = new MaximumFinder(data, start, start + split);
        final MaximumFinder right = new MaximumFinder(data, start + split, end);
        return Math.max(left.fork().join(), right.fork().join());
    }

    private Integer computeDirectly() {
        System.out.println(Thread.currentThread() + "computing: " + start
                + " to " + end);
        int max = Integer.MIN_VALUE;
        for (int i = start; i < end; i++) {
            if (data[i] > max) {
                max = data[i];
            }
        }
        return max;
    }
}