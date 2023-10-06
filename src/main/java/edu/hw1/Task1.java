package edu.hw1;

import java.util.regex.*;

public class Task1 {
    final static Pattern pattern = Pattern.compile("^(\\d+):([0-5][0-9])");

    public static int minutesToSecond(String time) {
        if (!pattern.matcher(time).matches()) {
            return -1;
        }
        String[] splitStr = time.split(":");
        int seconds = Integer.parseInt(splitStr[0]) * 60 + Integer.parseInt(splitStr[1]);
        return seconds;
    }
}
