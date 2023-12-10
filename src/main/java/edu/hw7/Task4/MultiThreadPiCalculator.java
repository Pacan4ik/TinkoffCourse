package edu.hw7.Task4;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Stream;

public class MultiThreadPiCalculator implements PiCalculator {
    private final int threads;

    private static final Function<Integer, Integer> GET_CIRCLE_COUNT = (localPoints) -> {
        double x;
        double y;
        int circleCount = 0;
        int localTotalCount = 0;
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (; localTotalCount < localPoints; localTotalCount++) {
            x = threadLocalRandom.nextDouble();
            y = threadLocalRandom.nextDouble();
            if (x * x + y * y <= 1) {
                circleCount++;
            }
        }
        return circleCount;
    };

    public MultiThreadPiCalculator(int threads) {
        if (threads <= 0) {
            throw new IllegalArgumentException();
        }
        this.threads = threads;
    }

    @Override
    public double calculate(int points) {

        int localPoints = points / threads;
        int extraPoints = points % threads;

        Callable<Integer> callable = () -> GET_CIRCLE_COUNT.apply(localPoints);

        var tasks = new ArrayList<>(Stream.generate(() -> callable).limit(threads - 1)
            .toList());
        tasks.add(() -> GET_CIRCLE_COUNT.apply(localPoints + extraPoints));

        long circleCount = 0;
        try (ExecutorService executorService = Executors.newFixedThreadPool(threads)) {
            var futures = executorService.invokeAll(tasks);
            for (var f : futures) {
                circleCount += f.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        //CHECKSTYLE:OFF: checkstyle:MagicNumber
        return 4 * ((double) circleCount / points);
    }

}
