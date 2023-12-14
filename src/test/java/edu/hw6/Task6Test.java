package edu.hw6;

import edu.hw6.task6.PortChecker;
import java.io.IOException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {

    @Test
    @Disabled
    //не получилось замокать
    void testTryPort() throws Exception {
        try(MockedStatic mocked = mockStatic(PortChecker.class)){
            mocked.when(()->PortChecker.tryPort(8080)).thenThrow(new IOException());
            assertTrue(PortChecker.tryPort(8080).tcp());
            mocked.verify(()-> PortChecker.tryPort(any()));
        }
    }

}
