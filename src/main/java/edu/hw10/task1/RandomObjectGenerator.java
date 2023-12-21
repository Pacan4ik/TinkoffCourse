package edu.hw10.task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Objects;

public class RandomObjectGenerator {
    private static final int DEFAULT_MIN = 0;
    private static final int DEFAULT_MAX = 100;
    private static final boolean DEFAULT_NOTNULL = false;

    private int minValue = DEFAULT_MIN;
    private int maxValue = DEFAULT_MAX;
    private boolean notNullValue = DEFAULT_NOTNULL;

    public RandomObjectGenerator(int minValue, int maxValue, boolean notNullValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.notNullValue = notNullValue;
    }

    public RandomObjectGenerator() {

    }

    public <T> T nextObject(Class<T> tClass) {
        var constructors = tClass.getConstructors();
        if (constructors.length == 0) {
            throw new RuntimeException("Class has no public constructors");
        }
        for (var c : constructors) {
            try {
                return (T) c.newInstance(generateParams(c.getParameters()));
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException ignored) {
            }
        }
        throw new RuntimeException("No such suitable constructors");
    }

    public <T> T nextObject(Class<T> tClass, String fabricMethodName) {
        var methods = tClass.getMethods();
        for (var m : methods) {
            if (m.getName().equals(fabricMethodName)) {
                try {
                    return (T) m.invoke(tClass, generateParams(m.getParameters()));
                } catch (InvocationTargetException | IllegalAccessException ignored) {
                }
            }
        }
        throw new RuntimeException("No such suitable methods");
    }

    private Object[] generateParams(Parameter[] parameters) {
        Object[] randomParams = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            Min minAnnot = parameters[i].getAnnotation(Min.class);
            Max maxAnnot = parameters[i].getAnnotation(Max.class);
            NotNull notNullAnnot = parameters[i].getAnnotation(NotNull.class);
            int min = Objects.isNull(minAnnot) ? minValue : minAnnot.value();
            int max = Objects.isNull(maxAnnot) ? maxValue : maxAnnot.value();
            boolean notNull = !Objects.isNull(notNullAnnot) || notNullValue;
            randomParams[i] = generateRandomValue(parameters[i].getType(), min, max, notNull);
        }
        return randomParams;
    }

    private Object generateRandomValue(Class<?> type, int min, int max, boolean notNull) {
        for (var expectedType : ExpectedTypes.values()) {
            if (expectedType.match(type)) {
                try {
                    return expectedType.getRandom(min, max, notNull);
                } catch (RuntimeException e) {
                    return expectedType.getRandom(minValue, maxValue, notNullValue);
                }
            }
        }
        throw new RuntimeException("Unexpected type in constructor");
    }

}
