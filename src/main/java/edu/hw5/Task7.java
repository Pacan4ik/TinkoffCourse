package edu.hw5;

import java.util.regex.Pattern;

public class Task7 {
    private Task7() {
    }

    static boolean isContainsThirdZero(String string) {
        Pattern pattern = Pattern.compile("^[01]{2}0[01]*$");
        return pattern.matcher(string).matches();
    }

    static boolean isStartAndEndTheSame(String string) {
        Pattern pattern = Pattern.compile("^0([01]*0|)$|^1([01]*1|)$");
        return pattern.matcher(string).matches();
    }

    static boolean isLengthMoreOneNoMoreThree(String string) {
        Pattern pattern = Pattern.compile("^[01]{1,3}$");
        return pattern.matcher(string).matches();
    }

}
