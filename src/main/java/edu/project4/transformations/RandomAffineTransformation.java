package edu.project4.transformations;

import java.awt.Color;
import java.util.Random;

public class RandomAffineTransformation extends AffineTransformation {
    private static final double COEFF_BOUND = 1;
    private static final double OFFSET_BOUND = 3;
    private static final Random RANDOM = new Random();

    public RandomAffineTransformation(Color color) {
        super(
            getRandom(COEFF_BOUND),
            getRandom(COEFF_BOUND),
            getRandom(OFFSET_BOUND),
            getRandom(COEFF_BOUND),
            getRandom(COEFF_BOUND),
            getRandom(OFFSET_BOUND),
            color
        );
    }

    private static double getRandom(double bound) {
        return RANDOM.nextDouble(-1 * bound, bound);
    }
}
