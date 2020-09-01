package com.antologic.project.services;

import com.antologic.project.dtos.ProjectReportDto;
import com.antologic.project.dtos.UserRaportDto;
import com.antologic.project.reports.TimeRange;

public interface ReportService {
    UserRaportDto getUserReport(String login, TimeRange timeRange);

    ProjectReportDto getProjectReport(String projectName, TimeRange timeRange);
}
