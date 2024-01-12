package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    @DisplayName("Кто вызвал функию (Task4.callingInfo)")
    void callMethod() {
        //given

        //when
        Task4.CallingInfo info = Task4.callingInfo();
        String className = info.className();
        String methodName = info.methodName();

        //then
        assertEquals(className, this.getClass().getName());
        assertEquals(
            methodName,
            new Throwable()
                .getStackTrace()[0]
                .getMethodName()
        );
    }

    //или лучше так?
    @Test
    @DisplayName("Кто вызвал функию (Task4.callingInfo)")
    void callMethod2() {
        //given

        //when
        Task4.CallingInfo info = Task4.callingInfo();
        String className = info.className();
        String methodName = info.methodName();

        //then
        assertEquals(className, "edu.hw2.Task4Test");
        assertEquals(methodName, "callMethod2");
    }

}
