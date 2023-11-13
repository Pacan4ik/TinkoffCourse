package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task5Test {
    static Arguments[] correctLicensePlates(){
        return new Arguments[]{
            Arguments.of("А001АА98"),
            Arguments.of("А001АА198"),
            Arguments.of("А123ВЕ777"),
            Arguments.of("О777ОО177"),

        };
    }

    @ParameterizedTest
    @MethodSource("correctLicensePlates")
    void shouldPassValidation(String licensPlates){
        assertTrue(Task5.isLicensePlateValid(licensPlates));
    }

    static Arguments[] incorrectLicensePlates(){
        return new Arguments[]{
            Arguments.of("123ВЕ777"),
            Arguments.of("А123ВГ777"),
            Arguments.of("А123ВЕ7777"),
            Arguments.of("АА123ВЕ77"),
            Arguments.of("АА123В7"),


        };
    }

    @ParameterizedTest
    @MethodSource("incorrectLicensePlates")
    void shouldntPassValidation(String licensPlates){
        assertFalse(Task5.isLicensePlateValid(licensPlates));
    }


}
