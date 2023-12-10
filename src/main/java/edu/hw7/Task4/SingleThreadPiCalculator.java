package edu.hw7.Task4;

import java.util.Random;

public class SingleThreadPiCalculator implements PiCalculator {

    private static final Random RANDOM = new Random();

    @Override
    public double calculate(int points) {
        double x;
        double y;
        int circleCount = 0;
        int totalCount = 0;
        for (; totalCount < points; totalCount++) {
            x = RANDOM.nextDouble();
            y = RANDOM.nextDouble();
            if (x * x + y * y <= 1) {
                circleCount++;
            }
        }
        //CHECKSTYLE:OFF: checkstyle:MagicNumber
        return 4 * ((double) circleCount / totalCount);
    }
}
