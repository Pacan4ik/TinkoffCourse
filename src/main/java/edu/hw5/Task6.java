package edu.hw5;

import java.util.regex.Pattern;

public class Task6 {
    private Task6() {
    }

    static boolean isSubsequence(String s, String t) {
        Pattern pattern = Pattern.compile(String.format("^.*%s.*$", s));
        return pattern.matcher(t).matches();
    }
}
