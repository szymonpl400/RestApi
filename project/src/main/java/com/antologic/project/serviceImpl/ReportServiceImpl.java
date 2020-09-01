package com.antologic.project.serviceImpl;

import com.antologic.project.dtos.ProjectReportDto;
import com.antologic.project.dtos.ReportDto;
import com.antologic.project.dtos.UserRaportDto;
import com.antologic.project.entity.Project;
import com.antologic.project.entity.User;
import com.antologic.project.exceptions.NotFoundException;
import com.antologic.project.reports.TimeRange;
import com.antologic.project.repository.ProjectRepository;
import com.antologic.project.repository.TimesheetRepository;
import com.antologic.project.repository.UserRepository;
import com.antologic.project.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.antologic.project.utils.Util.*;

@Service
public class ReportServiceImpl implements ReportService {

    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportServiceImpl(TimesheetRepository timesheetRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public UserRaportDto getUserReport(String login, TimeRange timeRange) {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new NotFoundException());
        List<ReportDto> reportDtoList = new ArrayList<>();


        user.getProjects().forEach(tempProject -> {
            ReportDto raport = new ReportDto();
            raport.setProjectName(tempProject.getName());
            raport.setTotalUserTime(calcHours(tempProject.getTimesheets().stream().filter(timesheet ->
                    timesheet.getUser().equals(user)).filter(t -> t.getStarting().toLocalDate().isAfter(timeRange.range())
            ).collect(Collectors.toSet())));
            raport.setTotalCost(raport.getTotalUserTime().multiply(user.getCostPerHour()));
            raport.setUserLogin(user.getLogin());
            reportDtoList.add(raport);
        });

        UserRaportDto userRaportDto = new UserRaportDto(reportDtoList,
                BigDecimal.valueOf(calculateTotalExpensesFromAllProjectsOrSingle(reportDtoList)));
        return userRaportDto;
    }

    public ProjectReportDto getProjectReport(String projectName, TimeRange timeRange) {
        Project project = projectRepository.findByName(projectName).orElseThrow(() -> new NotFoundException());
        List<ReportDto> reportDtos = new ArrayList<>();
        ReportDto raport = new ReportDto();
        project.getUsers().forEach(tempUser -> {

            raport.setProjectName(project.getName());
            raport.setUserLogin(tempUser.getLogin());
            raport.setTotalUserTime(calcHours(tempUser.getTimesheets().stream().filter(timesheet ->
                    timesheet.getProject().equals(project)).filter(t -> t.getStarting().toLocalDate().isAfter(timeRange.range())
            ).collect(Collectors.toSet())));
            raport.setTotalCost(raport.getTotalUserTime().multiply(tempUser.getCostPerHour()));
            raport.setUserLogin(tempUser.getLogin());
            reportDtos.add(raport);
        });

        ProjectReportDto projectReportDto = new ProjectReportDto(reportDtos,
                calculateTotalExpensesFromAllProjectsOrSingle(reportDtos),
                BigDecimal.valueOf(calcHoursByReports(reportDtos)),
                calculateTotalExpensesFromAllProjectsOrSingle(reportDtos) > project.getBudget().doubleValue());
        return projectReportDto;
    }
}