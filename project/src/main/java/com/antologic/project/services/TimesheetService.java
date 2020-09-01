package com.antologic.project.services;

import com.antologic.project.dtos.TimesheetDto;
import com.antologic.project.forms.CreateTimesheetForm;

import java.util.List;
import java.util.UUID;

public interface TimesheetService {
    void createTimesheet(String projectName, String login, CreateTimesheetForm createTimesheetForm);

    List<TimesheetDto> getAllTimesheets(String login);

    void deleteTimesheet(UUID timesheetUUID);

    void editTimesheet(UUID timesheetUUID, CreateTimesheetForm createTimesheetForm);
}
