package com.antologic.project.utils;

import com.antologic.project.dtos.ReportDto;
import com.antologic.project.entity.Project;
import com.antologic.project.entity.Timesheet;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class Util {

    public static boolean isWithinTheTimeFrame(Project project, Timesheet timesheet) {
        return project.getBeginning().isBefore(timesheet.getStarting().toLocalDate()) && project.getFinishing().isAfter(timesheet.getEnding().toLocalDate());
    }

    public static BigDecimal calcBudget(Project project) {
        Set<Timesheet> timesheetList = project.getTimesheets();
        BigDecimal costs = new BigDecimal(0);
        for (Timesheet timesheet : timesheetList) {
            costs = costs.add(calcExpenses(timesheet), new MathContext(5));
        }
        return (costs.divide(project.getBudget(), RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
    }

    public static BigDecimal calcExpenses(Timesheet timesheet) {
        double hours = ((double) Duration.between(timesheet.getStarting(), timesheet.getEnding()).toMinutes()) / 60;
        return timesheet.getUser().getCostPerHour().multiply(BigDecimal.valueOf(hours));
    }

    private static double calculateHoursFromOneTimesheet(Timesheet timesheet) {
        return ((double) Duration.between(timesheet.getStarting(), timesheet.getEnding()).toMinutes()) / 60;
    }

    public static BigDecimal calcHours(Set<Timesheet> timesheets) {
        return BigDecimal.valueOf(timesheets.stream()
                .mapToDouble(Util::calculateHoursFromOneTimesheet).sum());
    }

    public static double calcHoursByReports(List<ReportDto> reports) {
        return reports.stream().mapToDouble(report -> report.getTotalUserTime().doubleValue()).sum();
    }

    public static double calculateTotalExpensesFromAllProjectsOrSingle(List<ReportDto> reportDtos) {
        return reportDtos.stream().map(ReportDto::getTotalCost).collect(Collectors.toList())
                .stream().mapToDouble(BigDecimal::doubleValue).sum();

    }
}
