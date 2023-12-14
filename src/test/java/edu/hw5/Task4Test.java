package edu.hw5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task4Test {
    static Arguments[] correctPasswords(){
        return new Arguments[]{
            Arguments.of("pa~sword"),
            Arguments.of("pa!sword"),
            Arguments.of("pa@sword"),
            Arguments.of("pa#sword"),
            Arguments.of("pa$sword"),
            Arguments.of("pa%sword"),
            Arguments.of("pa^sword"),
            Arguments.of("pa&sword"),
            Arguments.of("pa*sword"),
            Arguments.of("pa|sword"),
            Arguments.of("~password"),
            Arguments.of("password|"),
            Arguments.of("pa$%word"),
            Arguments.of("^password&"),
            Arguments.of("%")
        };
    }

    @ParameterizedTest
    @MethodSource("correctPasswords")
    void shouldPassValidation(String password){
        assertTrue(Task4.isPasswordValid(password));
    }

    @Test
    void shouldntPassValidation(){
        //given
        String password = "password";

        //when
        boolean isValid = Task4.isPasswordValid(password);

        //then
        assertFalse(isValid);

    }
}
