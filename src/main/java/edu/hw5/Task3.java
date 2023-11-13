package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    private Task3() {
    }

    static Optional<LocalDate> parseDate(String string) {
        for (var format : Formats.values()) {
            try {
                return Optional.of(format.convert(string));
            } catch (DateTimeParseException ignored) {
            }
        }
        return Optional.empty();
    }

    private enum Formats {
        STANDART(LocalDate::parse),
        SINGLE_DAY(string -> LocalDate.parse(string, DateTimeFormatter.ofPattern("yyyy-MM-d"))),

        DMYYY_WITH_SLASH(string -> LocalDate.parse(string, DateTimeFormatter.ofPattern("d/M/yyyy"))),

        DMYY_WITH_SLASH(string -> LocalDate.parse(string, DateTimeFormatter.ofPattern("d/M/yy"))),

        TOMORROW(string -> {
            if (string.equalsIgnoreCase("tomorrow")) {
                return LocalDate.now().plusDays(1);
            } else {
                throw new DateTimeParseException("Doesn't match \"tomorrow\"", string, 0);
            }
        }),

        TODAY(string -> {
            if (string.equalsIgnoreCase("today")) {
                return LocalDate.now();
            } else {
                throw new DateTimeParseException("Doesn't match \"today\"", string, 0);
            }
        }),

        YESTERDAY(string -> {
            if (string.equalsIgnoreCase("yesterday")) {
                return LocalDate.now().plusDays(-1);
            } else {
                throw new DateTimeParseException("Doesn't match \"yesterday\"", string, 0);
            }
        }),

        DAYSAGO(string -> {
            Pattern pattern = Pattern.compile("^(\\d+) day(s*) ago$");
            Matcher matcher = pattern.matcher(string);
            if (matcher.matches()) {
                return LocalDate.now().plusDays(-Long.parseLong(matcher.group(1)));
            } else {
                throw new DateTimeParseException(String.format("Doesn't match pattern %s", pattern), string, 0);
            }

        });

        private final Function<String, LocalDate> functionConvert;

        Formats(Function<String, LocalDate> functionConvert) {
            this.functionConvert = functionConvert;
        }

        public LocalDate convert(String string) {
            return this.functionConvert.apply(string);
        }
    }
}
