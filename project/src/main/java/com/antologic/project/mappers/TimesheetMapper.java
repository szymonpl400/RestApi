package com.antologic.project.mappers;

import com.antologic.project.dtos.TimesheetDto;
import com.antologic.project.entity.Timesheet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "Spring")
public interface TimesheetMapper {

    @Mapping(source = "project.name", target = "projectName")
    TimesheetDto timesheetToTimesheetDto(Timesheet timesheet);
}
