package edu.hw10.task1;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum ExpectedTypes {
    INT_PRIMITIVE(
        int.class,
        (int min, int max, boolean notNull) -> {
            if (min >= max) {
                throw new IllegalArgumentException("Wrong bounds for integer type");
            }
            Random random = new Random();
            return random.nextInt(min, max);
        }
    ),

    SHORT_PRIMITIVE(
        short.class,
        (int min, int max, boolean notNull) -> {
            if (min >= max || min < Short.MIN_VALUE || max > Short.MAX_VALUE) {
                throw new IllegalArgumentException("Wrong bounds for short type");
            }
            Random random = new Random();
            return (short) random.nextInt(min, max);
        }
    ),
    STRING_PRIMITIVE(
        String.class,
        (int min, int max, boolean notNull) -> {
            int realMin = Math.max(min, 0);
            if (realMin >= max) {
                throw new IllegalArgumentException("Wrong bounds for String length");
            }
            Random random = new Random();
            if (!notNull && random.nextBoolean()) {
                return null;
            }
            int length = random.nextInt(realMin, max);
            if (length == 0) {
                return "";
            }
            return Stream.generate(() -> String.valueOf((char) random.nextInt())).limit(length)
                .collect(Collectors.joining());
        }
    ),
    BOOLEAN_PRIMITIVE(
        boolean.class,
        (int min, int max, boolean notNull) -> new Random().nextBoolean()
    ),
    ARR_INT_PRIMITIVE(
        int[].class,
        (int min, int max, boolean notNull) -> {
            int realMin = Math.max(min, 0);
            if (realMin >= max) {
                throw new IllegalArgumentException("Wrong bounds for Array length");
            }
            Random random = new Random();
            if (!notNull && random.nextBoolean()) {
                return null;
            }
            int[] ints = new int[random.nextInt(realMin, max)];
            for (int i = 0; i < ints.length; i++) {
                ints[i] = random.nextInt();
            }
            return ints;
        }
    );

    private final Class<?> typeClass;
    private final ParamGenerator generator;

    ExpectedTypes(Class<?> typeClass, ParamGenerator generator) {
        this.typeClass = typeClass;
        this.generator = generator;
    }

    public boolean match(Class<?> other) {
        //return typeClass.isAssignableFrom(other);
        return typeClass == other;
    }

    public Object getRandom(int min, int max, boolean notNull) {
        return generator.apply(min, max, notNull);
    }

    @FunctionalInterface
    interface ParamGenerator {
        Object apply(int min, int max, boolean notNull);
    }
}


