package com.antologic.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectReportDto {
    private List<ReportDto> userRaports;
    private double totalProjectCost;
    private BigDecimal numberOfHoursSpent;
    private boolean isExceeded;
}
