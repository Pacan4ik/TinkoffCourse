package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;

public class Task1Test {
    @Test
    void shouldReturnHello()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //given
        Class<?> helloClass;
        try (
            var unloaded = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.isToString())
                .intercept(FixedValue.value("Hello, ByteBuddy"))
                .make()
        ) {
            helloClass = unloaded.load(getClass().getClassLoader()).getLoaded();
        }

        //when
        Object obj = helloClass.getConstructor().newInstance();

        //then
        Assertions.assertEquals("Hello, ByteBuddy", obj.toString());
    }
}
