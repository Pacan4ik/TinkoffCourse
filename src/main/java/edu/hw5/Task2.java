package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task2 {
    private static final int BAD_DAY = 13;
    private static final int MONTHS_IN_YEAR = 12;

    private Task2() {
    }

    static List<LocalDate> getBadFridays(int year) {
        if (year < 0) {
            throw new IllegalArgumentException("year can't be less than 0");
        }
        LocalDate badDayInMonth = LocalDate.ofYearDay(year, BAD_DAY);
        List<LocalDate> fridays = new ArrayList<>();
        for (int i = 0; i < MONTHS_IN_YEAR; i++) {
            if (badDayInMonth.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays.add(badDayInMonth);
            }
            badDayInMonth = badDayInMonth.plusMonths(1);
        }
        return Collections.unmodifiableList(fridays);
    }

    static LocalDate getNextBadFriday(LocalDate date) {
        LocalDate badFriday = date;
        while (badFriday.getDayOfMonth() != BAD_DAY) {
            badFriday = badFriday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return badFriday;
    }

}
