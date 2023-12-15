package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task2Test {

    public static class SumToMultiplyInterceptor {
        public static int multiply(int a, int b) {
            return a * b;
        }
    }

    static {
        ByteBuddyAgent.install();
    }

    @Test
    @Disabled
    void shouldChangeSumToMultiply()
        throws NoSuchMethodException {
        //given
        try (var unloaded = new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sumInts"))
            //.intercept(MethodDelegation.to(new SumToMultiplyInterceptor()))
            .intercept(MethodCall.invoke(
                    SumToMultiplyInterceptor.class.getMethod("multiply", int.class, int.class)
                )
                .withAllArguments())
            .make()
        ) {
            unloaded.load(
                ArithmeticUtils.class.getClassLoader(),
                ClassReloadingStrategy.fromInstalledAgent()
            );
        }

        //when
        var obj = new ArithmeticUtils();

        //then
        Assertions.assertEquals(20, obj.sumInts(4, 5));
    }

}
