package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.Opcodes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task3Test {

    static Class<?> fibClass;

    static {
        try (var unloaded = new ByteBuddy()
            .subclass(Object.class)
            .name("edu.hw11.FibCalc")
            .defineMethod("fib", int.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(new FibCodeAppender()))
            .make()
        ) {
            fibClass = unloaded.load(null, ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
        }
    }

    @Test
    void shouldContainMethod() {
        Assertions.assertDoesNotThrow(()->fibClass.getMethod("fib", int.class));
    }

    @Test
    void shouldGenerateFib() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //given
        Method fib = fibClass.getMethod("fib", int.class);

        //when
        int res = (int) fib.invoke(null, 10);

        //then
        Assertions.assertEquals(55, res);
    }

    @Test
    void shouldReturnNegOne() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //given
        Method fib = fibClass.getMethod("fib", int.class);

        //when
        int res = (int) fib.invoke(null, 0);

        //then
        Assertions.assertEquals(-1, res);
    }
}
