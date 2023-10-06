package edu.hw1;

import java.util.regex.Pattern;

public class Task1 {
    private Task1() {
    }

    private static final Pattern PATTERN = Pattern.compile("^(\\d+):([0-5][0-9])");
    private final static int SECONDS = 60;

    public static int minutesToSecond(String time) {
        if (!PATTERN.matcher(time).matches()) {
            return -1;
        }
        String[] splitStr = time.split(":");
        int seconds = Integer.parseInt(splitStr[0]) * SECONDS + Integer.parseInt(splitStr[1]);
        return seconds;
    }
}
