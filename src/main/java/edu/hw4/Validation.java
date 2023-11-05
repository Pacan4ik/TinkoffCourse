package edu.hw4;

import java.util.Objects;
import java.util.function.Function;

@SuppressWarnings("MagicNumber")
public class Validation {
    private static final int MIN_AGE = 0;
    private static final int MAX_AGE = 100;
    private static final int MIN_HEIGHT = 0;
    private static final int MAX_HEIGHT = 5000;
    private static final int MIN_WEIGHT = 0;
    private static final int MAX_WEIGHT = 200000;

    public enum ValidationError {
        NAME_NULL(animal -> Objects.isNull(animal.name())),
        TYPE_NULL(animal -> Objects.isNull(animal.type())),
        SEX_NULL(animal -> Objects.isNull(animal.sex())),
        WRONG_AGE(animal -> animal.age() < MIN_AGE || animal.age() > MAX_AGE),
        WRONG_HEIGHT(animal -> animal.height() < MIN_HEIGHT || animal.height() > MAX_HEIGHT),
        WRONG_WEIGHT(animal -> animal.weight() < MIN_WEIGHT || animal.height() > MAX_WEIGHT);

        private final Function<Animal, Boolean> function;

        ValidationError(Function<Animal, Boolean> function) {
            this.function = function;
        }

        public Function<Animal, Boolean> getValidationFunction() {
            return function;
        }

        @Override public String toString() {
            return this.name();
        }
    }
}
