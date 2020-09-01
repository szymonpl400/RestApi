package com.antologic.project.controllers;

import com.antologic.project.dtos.TimesheetDto;
import com.antologic.project.forms.CreateTimesheetForm;
import com.antologic.project.serviceImpl.TimesheetServiceImpl;
import com.antologic.project.services.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

    private TimesheetService timesheetService;

    @Autowired
    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTimesheet(@RequestParam(name = "project") String projectName,
                                @RequestParam(name = "user") String login,
                                @Valid @RequestBody CreateTimesheetForm createTimesheetForm) {
        timesheetService.createTimesheet(projectName, login, createTimesheetForm);
    }

    @GetMapping
    public List<TimesheetDto> createTimesheet(@RequestParam(name = "user") String login) {
        return timesheetService.getAllTimesheets(login);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteTimesheet(@RequestParam(name = "timesheetUUID") UUID timesheetUUID) {
        timesheetService.deleteTimesheet(timesheetUUID);
    }

    @PutMapping
    public void editTimesheet(@RequestParam(name = "timesheetUUID") UUID timesheetUUID,
                              @Valid @RequestBody CreateTimesheetForm createTimesheetForm) {
        timesheetService.editTimesheet(timesheetUUID, createTimesheetForm);
    }
}
