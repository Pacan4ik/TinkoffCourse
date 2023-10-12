package edu.hw1;

import java.util.regex.Pattern;

final public class Task1 {
    private Task1() {
    }

    private static final int ERRCODE = -1;
    private static final Pattern PATTERN = Pattern.compile("^(\\d+):([0-5][0-9])");
    private static final int SECONDS_IN_MINUTE = 60;

    public static int minutesToSecond(String time) {
        if (!PATTERN.matcher(time).matches()) {
            return ERRCODE;
        }
        String[] splitStr = time.split(":");
        int seconds = Integer.parseInt(splitStr[0]) * SECONDS_IN_MINUTE + Integer.parseInt(splitStr[1]);
        if (seconds < 0) {
            return ERRCODE;
        }
        return seconds;
    }
}
