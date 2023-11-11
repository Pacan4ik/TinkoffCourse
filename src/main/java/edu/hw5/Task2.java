package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Task2 {
    private static final int BAD_DAY = 13;
    private static final int MONTHS_IN_YEAR = 12;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static List<String> getBadFridays(int year) {
        if (year < 0){
            throw new IllegalArgumentException("year can't be less than 0");
        }
        LocalDate badDayInMonth = LocalDate.ofYearDay(year, BAD_DAY);
        List<String> fridays = new ArrayList<>();
        for (int i = 0; i < MONTHS_IN_YEAR; i++) {
            if (badDayInMonth.getDayOfWeek() == DayOfWeek.FRIDAY){
                fridays.add(badDayInMonth.format(DATE_TIME_FORMATTER));
            }
            badDayInMonth = badDayInMonth.plusMonths(1);
        }
        return Collections.unmodifiableList(fridays);
    }

    static LocalDate getNextBadFriday(LocalDate date){
        TemporalAdjuster badFriday = TemporalAdjusters.next(DayOfWeek.FRIDAY);
        while (badFriday.
        System.out.println(badFriday);
        return null;
    }


    public static void main(String[] args) {
        getNextBadFriday(LocalDate.now());
    }
}
