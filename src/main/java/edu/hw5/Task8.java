package edu.hw5;

import java.util.regex.Pattern;

public class Task8 {
    private Task8() {
    }

    static boolean isLengthIsOdd(String string) {
        Pattern pattern = Pattern.compile("^[10]([01]{2})*$");
        return pattern.matcher(string).matches();
    }

    static boolean isLengthIsOddWhenStartsWithZeroOrEvenWithOne(String string) {
        Pattern pattern = Pattern.compile("^0([01]{2})*$|^(10|11)([01]{2})*$");
        return pattern.matcher(string).matches();
    }

    static boolean isAmountZerosMultipleOfThree(String string) {
        Pattern pattern = Pattern.compile("^(1*01*01*01*)+$|^1*$");
        return pattern.matcher(string).matches();
    }

    static boolean isEveryOddIsOne(String string) {
        Pattern pattern = Pattern.compile("^1([01]1)*[01]?$");
        return pattern.matcher(string).matches();
    }
}
