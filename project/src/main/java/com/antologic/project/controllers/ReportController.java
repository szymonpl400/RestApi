package com.antologic.project.controllers;

import com.antologic.project.dtos.ProjectReportDto;
import com.antologic.project.dtos.UserRaportDto;
import com.antologic.project.reports.TimeRange;
import com.antologic.project.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping(value = "/user")
    public UserRaportDto showUserReport(@RequestParam(name = "user") String login,
                                        @RequestParam(name = "timeRange") TimeRange timeRange) {
        return reportService.getUserReport(login, timeRange);
    }

    @GetMapping(value = "/project")
    public ProjectReportDto showProjectReport(@RequestParam(name = "project") String projectName,
                                              @RequestParam(name = "timeRange") TimeRange timeRange) {
        return reportService.getProjectReport(projectName, timeRange);
    }
}
