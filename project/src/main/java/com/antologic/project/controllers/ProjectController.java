package com.antologic.project.controllers;

import com.antologic.project.dtos.ProjectDto;
import com.antologic.project.dtos.ProjectMenagerDto;
import com.antologic.project.dtos.ProjectViewDto;
import com.antologic.project.entity.ProjectView;
import com.antologic.project.forms.CreateProjectForm;
import com.antologic.project.forms.FilterProjectForm;
import com.antologic.project.serviceImpl.ProjectServiceImpl;
import com.antologic.project.services.ProjectService;
import com.antologic.project.services.ProjectViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectViewService projectViewService;

    @Autowired
    public ProjectController(ProjectService projectService, ProjectViewService projectViewService) {
        this.projectService = projectService;
        this.projectViewService = projectViewService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProjectDto createProject(@Valid @RequestBody CreateProjectForm createProjectForm) {
        return projectService.saveProject(createProjectForm);
    }

    @PutMapping
    public boolean addUser(@RequestParam(name = "name") String projectName,
                           @RequestParam(name = "user") long userId) {
        return projectService.addToProject(projectName, userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete_user")
    public boolean deleteUserFromProject(@RequestParam(name = "name") String projectName,
                                         @RequestParam(name = "login") String userLogin) {
        return projectService.removeFromProject(projectName, userLogin);
    }

    @PutMapping("/project/{id}")
    public void editProject(@Valid @PathVariable Long id, @RequestBody CreateProjectForm createProjectForm) {
        projectService.updateProject(createProjectForm, id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public boolean deleteProject(@RequestParam(name = "name") String projectName) {
        return projectService.removeProject(projectName);
    }

    @GetMapping
    public List<ProjectMenagerDto> showAllProjects() {
        return projectService.findAllWithBudget();
    }

    @GetMapping("/search")
    public List<ProjectViewDto> search(@RequestBody FilterProjectForm search, Pageable pageable) {
        return projectViewService.findWithFilter(search, pageable);
    }

}
