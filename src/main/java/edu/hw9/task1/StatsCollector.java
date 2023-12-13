package edu.hw9.task1;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StatsCollector implements AutoCloseable {
    private final Map<String, Stats> statsMap = new ConcurrentHashMap<>();
    private final ExecutorService executor;
    private static final int TIMEOUT_SECONDS = 10;

    public StatsCollector(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public void push(String metricName, double[] data) {
        if (executor.isShutdown()) {
            throw new RuntimeException("Executor service has been closed");
        }
        CompletableFuture.runAsync(() -> computeStats(metricName, data), executor)
            .orTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    public Map<String, Stats> stats() {
        return Map.copyOf(statsMap);
    }

    private void computeStats(String metricName, double[] data) {
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
        statsMap.put(metricName, new Stats(sum, average, max, min));
    }

    @Override
    public void close() {
        executor.close();
    }

    public record Stats(double sum, double average, double max, double min) {
    }
}
