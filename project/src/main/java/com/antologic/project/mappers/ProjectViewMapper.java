package com.antologic.project.mappers;

import com.antologic.project.dtos.ProjectViewDto;
import com.antologic.project.entity.ProjectView;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface ProjectViewMapper {

    List<ProjectViewDto> projectViewsToProjectViewsDto(List<ProjectView> projectView);


}
