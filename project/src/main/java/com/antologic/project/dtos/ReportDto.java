package com.antologic.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReportDto {
    private String userLogin;
    private String projectName;
    private BigDecimal totalUserTime;
    private BigDecimal totalCost;
}
