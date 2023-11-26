package edu.hw7;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class Task1 {
    public static class Counter {
        private static AtomicLong value = new AtomicLong(0);

        public static void incrementValue() {
            value.getAndIncrement();
        }

        public static long getValue() {
            return value.longValue();
        }
    }

    public static class MultiThreading {
        public static long incrementWithMultiThreading(int threads, int amountPerThread) {
            Callable<Void> callable = () -> {
                for (int i = 0; i < amountPerThread; i++) {
                    Counter.incrementValue();
                }
                return null;
            };

            var tasks = Stream.generate(() -> callable).limit(threads).toList();

            try (ExecutorService executorService = Executors.newFixedThreadPool(threads)) {
                List<Future<Void>> futures = executorService.invokeAll(tasks);
                for (var f : futures) {
                    f.get();
                }
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Counter.getValue();
        }

    }

}
