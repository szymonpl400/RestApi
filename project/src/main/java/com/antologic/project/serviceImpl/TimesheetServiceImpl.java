package com.antologic.project.serviceImpl;

import com.antologic.project.dtos.TimesheetDto;
import com.antologic.project.entity.Project;
import com.antologic.project.entity.Timesheet;
import com.antologic.project.entity.User;
import com.antologic.project.exceptions.NotFoundException;
import com.antologic.project.forms.CreateTimesheetForm;
import com.antologic.project.mappers.TimesheetMapper;
import com.antologic.project.repository.ProjectRepository;
import com.antologic.project.repository.TimesheetRepository;
import com.antologic.project.repository.UserRepository;
import com.antologic.project.services.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.antologic.project.utils.Util.isWithinTheTimeFrame;

@Service
public class TimesheetServiceImpl implements TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TimesheetMapper timesheetMapper;

    @Autowired
    public TimesheetServiceImpl(TimesheetRepository timesheetRepository, UserRepository userRepository,
                                ProjectRepository projectRepository, TimesheetMapper timesheetMapper) {
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.timesheetMapper = timesheetMapper;
    }

    public void createTimesheet(String projectName, String login, CreateTimesheetForm createTimesheetForm) {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new NotFoundException());
        Project project = projectRepository.findByName(projectName).orElseThrow(() -> new NotFoundException());
        Timesheet timesheet = new Timesheet(createTimesheetForm.getStarting(), createTimesheetForm.getEnding(),
                project, user);
        if (isWithinTheTimeFrame(project, timesheet)) {
            user.addTimesheets(timesheet);
            userRepository.save(user);
        }
    }

    public List<TimesheetDto> getAllTimesheets(String login) {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new NotFoundException());
        List<TimesheetDto> timesheetDtos = new ArrayList<>();
        timesheetRepository.findByUser(user).forEach(tempTimesheet -> timesheetDtos.add(timesheetMapper.timesheetToTimesheetDto(tempTimesheet)));
        return timesheetDtos;
    }

    public void deleteTimesheet(UUID timesheetUUID) {
        timesheetRepository.delete(timesheetRepository.findByUuid(timesheetUUID).orElseThrow(() -> new NotFoundException()));
    }

    public void editTimesheet(UUID timesheetUUID, CreateTimesheetForm createTimesheetForm) {
        Timesheet timesheet = timesheetRepository.findByUuid(timesheetUUID).orElseThrow(() -> new NotFoundException());
        timesheet.setEnding(createTimesheetForm.getEnding());
        timesheet.setStarting(createTimesheetForm.getStarting());

        if (isWithinTheTimeFrame(timesheet.getProject(), timesheet)) {
            timesheetRepository.save(timesheet);
        }
    }
}