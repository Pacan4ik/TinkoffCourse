package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    private Task1() {
    }

    private static final Pattern STRING_PATTERN =
        Pattern.compile("(\\d{4}-\\d{2}-\\d{2}, \\d{1,2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{1,2}:\\d{2})");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd, H:mm");

    static String getComputerClubStats(String[] strings) {
        Duration duration = Duration.ZERO;
        for (String durStr : strings) {
            Matcher matcher = STRING_PATTERN.matcher(durStr);
            if (!matcher.matches()) {
                throw new IllegalArgumentException(String.format(
                    "%s doesn't match format %s",
                    durStr,
                    STRING_PATTERN.pattern()
                ));
            }
            LocalDateTime startDate = LocalDateTime.parse(matcher.group(1), DATETIME_FORMATTER);
            LocalDateTime endDate = LocalDateTime.parse(matcher.group(2), DATETIME_FORMATTER);
            duration = duration.plus(Duration.between(startDate, endDate));
        }
        duration = duration.dividedBy(strings.length);

        return String.format("%dч %dм", duration.toHours(), duration.toMinutesPart());
    }
}
