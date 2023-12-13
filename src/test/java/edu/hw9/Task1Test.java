package edu.hw9;

import edu.hw9.task1.StatsCollector;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    void shouldComputeStats() throws InterruptedException {
        //given
        double[] data = new double[] {10, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        StatsCollector statsCollector = new StatsCollector(2);

        //when
        statsCollector.push("data", data);
        Thread.sleep(200);

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
            executorService.invokeAny(Stream.generate(()
                -> callable).limit(200).toList());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }


        //then
        var res = statsCollector.stats();
        StatsCollector.Stats expected = new StatsCollector.Stats(1999000, 999.5, 1999, 0);
        assertThat(res.values()).allMatch(stats -> (stats.equals(expected)));

        statsCollector.close();

    }
}
