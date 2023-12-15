package edu.project5;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class Main {

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    private static final Options OPTIONS = new OptionsBuilder()
        .include(ReflectionBenchmark.class.getSimpleName())
        .shouldFailOnError(true)
        .shouldDoGC(true)
        .mode(Mode.AverageTime)
        .timeUnit(TimeUnit.NANOSECONDS)
        .forks(1)
        .warmupForks(1)
        .warmupIterations(1)
        .warmupTime(TimeValue.seconds(1))
        .measurementIterations(1)
        .measurementTime(TimeValue.seconds(120))
        .build();

    public static void main(String[] args) throws RunnerException {
        new ReflectionBenchmark().run(OPTIONS);
    }
}
