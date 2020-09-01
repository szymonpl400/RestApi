package com.antologic.project.services;

import com.antologic.project.dtos.ProjectDto;
import com.antologic.project.dtos.ProjectMenagerDto;
import com.antologic.project.dtos.ProjectViewDto;
import com.antologic.project.entity.ProjectView;
import com.antologic.project.forms.CreateProjectForm;
import com.antologic.project.forms.FilterProjectForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectService {

    ProjectDto saveProject(CreateProjectForm createProjectForm);

    boolean addToProject(String projectName, long userId);

    boolean removeFromProject(String projectName, String userLogin);

    void updateProject(CreateProjectForm createProjectForm, long projectId);

    boolean removeProject(String projectName);

    List<ProjectMenagerDto> findAllWithBudget();
}
