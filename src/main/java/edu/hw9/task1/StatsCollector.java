package edu.hw9.task1;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector implements AutoCloseable {
    private final ConcurrentMap<String, Stats> statsMap = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, double[]> dataMap = new ConcurrentHashMap<>();
    private final ExecutorService executor;

    public StatsCollector(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public void push(String metricName, double[] data) {
        if (executor.isShutdown()) {
            throw new RuntimeException("Executor service has been closed");
        }
        executor.execute(() -> computeStats(metricName, data));
    }

    public Map<String, Stats> stats() {
        return Map.copyOf(statsMap);
    }

    private void computeStats(String metricName, double[] data) {
        dataMap.merge(metricName, data, (a, b) -> {
                double[] res = new double[a.length + b.length];
                System.arraycopy(a, 0, res, 0, a.length);
                System.arraycopy(b, 0, res, a.length, b.length);
                return res;
            }
        );

        double[] doubles = dataMap.get(metricName);
        double[] curData = Arrays.copyOf(doubles, doubles.length);
        double sum = curData[0];
        double max = curData[0];
        double min = curData[0];
        for (int i = 1; i < curData.length; i++) {
            sum += curData[i];
            if (curData[i] > max) {
                max = curData[i];
            }
            if (curData[i] < min) {
                min = curData[i];
            }
        }
        double average = sum / curData.length;

        statsMap.put(metricName, new Stats(sum, average, max, min));

    }

    @Override
    public void close() {
        executor.close();
    }

    public record Stats(double sum, double average, double max, double min) {
    }
}
