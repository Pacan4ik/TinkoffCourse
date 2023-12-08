package edu.hw9;

import edu.hw9.task1.StatsCollector;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    void shouldComputeStats() {
        //given
        double[] data = new double[] {10, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        StatsCollector statsCollector = new StatsCollector(2);

        //when
        statsCollector.push("data", data);

        //then
        var res = statsCollector.stats();
        Assertions.assertEquals(1, res.size());
        assertThat(res).containsOnly(
            new AbstractMap.SimpleEntry<>(
                "data",
                new StatsCollector.Stats(55, 5.5, 10, 1)
            )
        );
        statsCollector.close();
    }

    @RepeatedTest(200)
    void shouldComputeStatsWithConcurrency() {
        //given
        int n = 2000;
        double[] data = new double[n];
        for (int i = 0; i < n; i++) {
            data[i] = i;
        }
        StatsCollector statsCollector = new StatsCollector(4);

        //when
        Callable<Void> callable = () -> {
            double[] doubles = Arrays.copyOf(data, n);
            statsCollector.push(doubles.toString(), doubles);
            return null;
        };
        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            executorService.invokeAll(Stream.generate(()
                -> callable).limit(200).toList());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //then
        var res = statsCollector.stats();
        Assertions.assertEquals(200, res.size());
        StatsCollector.Stats expected = new StatsCollector.Stats(1999000, 999.5, 1999, 0);
        assertThat(res.values()).allMatch(stats -> (stats.equals(expected)));

        statsCollector.close();

    }

    @Test
    void shouldLockThenReturningStats() {
        //given
        StatsCollector statsCollector = new StatsCollector(4);
        int n = 2000;
        for (int i = 0; i < n; i++) {
            statsCollector.push(Integer.toString(i), new double[n]);
        }

        //when
        double[] data = new double[n];
        for (int i = 0; i < n; i++) {
            data[i] = i;
        }
        List<Callable<Void>> callableList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int finalI = i;
            callableList.add(() -> {
                    double[] doubles = Arrays.copyOf(data, n);
                    statsCollector.push(Integer.toString(finalI), doubles);
                    return null;
                }
            );
        }
        new Thread(() -> {
            try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
                executorService.invokeAll(callableList);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        ).start();

        //then
        var res = statsCollector.stats();
        Assertions.assertEquals(2000, res.size());
        StatsCollector.Stats expected = new StatsCollector.Stats(1999000, 999.5, 1999, 0);
        StatsCollector.Stats anotherExpected = new StatsCollector.Stats(0, 0, 0, 0);
        assertThat(res.values()).allMatch(stats -> (stats.equals(expected) || stats.equals(anotherExpected)));

        statsCollector.close();
    }
}
