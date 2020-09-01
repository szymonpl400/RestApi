package com.antologic.project.mappers;

import com.antologic.project.dtos.ProjectDto;
import com.antologic.project.entity.Project;
import com.antologic.project.forms.CreateProjectForm;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface ProjectMapper {

    ProjectDto projectToProjectDto(Project project);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "uuid")
    @Mapping(ignore = true, target = "users")
    @Mapping(ignore = true, target = "timesheets")
    Project createProjectFormToProject(CreateProjectForm createProjectForm);
}
