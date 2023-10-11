package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task0Test {
    @Test
    @DisplayName("Привет мир (Task0.helloWorld)")
    void helloWorldTest() throws Exception {
        String text = tapSystemOut(Task0::helloWorld);

        assertEquals("Привет, мир!\n", text);
    }
}
