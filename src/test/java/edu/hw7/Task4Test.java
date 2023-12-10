package edu.hw7;

import edu.hw7.Task4.MultiThreadPiCalculator;
import edu.hw7.Task4.PiCalculator;
import edu.hw7.Task4.SingleThreadPiCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.text.DecimalFormat;

public class Task4Test {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##############");

    static Arguments[] arguments() {
        return new Arguments[] {
            Arguments.of(10_000_000, new SingleThreadPiCalculator()),               //~150ms
            Arguments.of(10_000_000, new MultiThreadPiCalculator(16)),      //~50ms
            Arguments.of(10_000_000, new MultiThreadPiCalculator(8)),       //~25ms
            Arguments.of(10_000_000, new MultiThreadPiCalculator(4)),       //~13ms
            Arguments.of(10_000_000, new MultiThreadPiCalculator(1)),       //~50ms
            //Погрешность 5*10^(-4)

            Arguments.of(100_000_000, new SingleThreadPiCalculator()),              //~1400ms
            Arguments.of(100_000_000, new MultiThreadPiCalculator(16)),     //~65ms
            Arguments.of(100_000_000, new MultiThreadPiCalculator(8)),      //~100ms
            Arguments.of(100_000_000, new MultiThreadPiCalculator(4)),      //~130ms
            Arguments.of(100_000_000, new MultiThreadPiCalculator(1)),      //~490ms
            //Погрешность 10^(-4)

            Arguments.of(1_000_000_000, new SingleThreadPiCalculator()),            //~13000ms
            Arguments.of(1_000_000_000, new MultiThreadPiCalculator(16)),   //~700ms
            Arguments.of(1_000_000_000, new MultiThreadPiCalculator(8)),    //~1000ms
            Arguments.of(1_000_000_000, new MultiThreadPiCalculator(4)),    //~1350ms
            Arguments.of(1_000_000_000, new MultiThreadPiCalculator(1)),    //~5000ms
            //Погрешность 5*10^(-5)
        };
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void performance(int points, PiCalculator calculator) {
        long start = System.nanoTime();
        double pi = calculator.calculate(points);
        long end = System.nanoTime();
        LOGGER.info(calculator.getClass().getSimpleName() + String.format(" %d точек", points));
        LOGGER.info((end - start) / 1_000_000 + " ms");
        LOGGER.info("Погрешность: " + DECIMAL_FORMAT.format(Math.abs(Math.PI - pi)));
    }
}
