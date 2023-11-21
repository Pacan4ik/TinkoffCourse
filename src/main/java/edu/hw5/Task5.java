package edu.hw5;

import java.util.regex.Pattern;

public class Task5 {
    private Task5() {
    }

    private static final Pattern PATTERN =
        Pattern.compile(String.format("^%1$s(?!000)\\d{3}%1$s{2}\\d{2,3}$", "[АВЕКМНОРСТУХ]"));

    static boolean isLicensePlateValid(String password) {
        return PATTERN.matcher(password).matches();
    }
}

