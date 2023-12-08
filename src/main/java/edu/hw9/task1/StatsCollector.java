package edu.hw9.task1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class StatsCollector implements AutoCloseable {
    private final Map<String, double[]> dataMap = new ConcurrentHashMap<>();

    private final ExecutorService executor;

    public StatsCollector(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
    }

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock pushLock = lock.readLock(); //shared
    private final Lock computeLock = lock.readLock(); //exclusive

    public void push(String metricName, double[] data) {
        pushLock.lock();
        try {
            dataMap.put(metricName, data);
        } finally {
            pushLock.unlock();
        }
    }

    public Map<String, Stats> stats() {
        computeLock.lock();
        Map<String, Future<Stats>> futureStatsMap;
        try {
            futureStatsMap = new HashMap<>();
            for (var entry : dataMap.entrySet()) {
                futureStatsMap.put(
                    entry.getKey(),
                    executor.submit(() -> computeStats(entry.getKey()))
                );
            }
            return futureStatsMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> {
                            try {
                                return e.getValue().get(5, TimeUnit.SECONDS);
                            } catch (InterruptedException | ExecutionException | TimeoutException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    )
                );
        } finally {
            computeLock.unlock();
        }

    }

    private Stats computeStats(String metric) {
        double[] data = dataMap.get(metric);
        double sum = data[0];
        double max = data[0];
        double min = data[0];
        for (int i = 1; i < data.length; i++) {
            sum += data[i];
            if (data[i] > max) {
                max = data[i];
            }
            if (data[i] < min) {
                min = data[i];
            }
        }
        double average = sum / data.length;
        return new Stats(sum, average, max, min);
    }

    @Override
    public void close() {
        executor.close();
    }

    public record Stats(double sum, double average, double max, double min) {
    }
}
