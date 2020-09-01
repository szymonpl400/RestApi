package com.antologic.project.reports;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;

public enum TimeRange implements Range {
    ALL(() -> (LocalDate.MIN)),
    YEAR(() -> (LocalDate.now().with(LocalDate.ofYearDay(LocalDate.now().getYear(), 1)))),
    MONTH(() -> (LocalDate.now().with(MonthDay.of(LocalDate.now().getMonthValue(), 1)))),
    WEEK(() -> (LocalDate.now().with(DayOfWeek.MONDAY)));

    private Range range;

    TimeRange(final Range range) {
        this.range = range;
    }

    @Override
    public LocalDate range() {
        return range.range();
    }
}
