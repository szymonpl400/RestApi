package com.antologic.project.serviceImpl;

import com.antologic.project.dtos.ProjectDto;
import com.antologic.project.dtos.ProjectMenagerDto;
import com.antologic.project.entity.Project;
import com.antologic.project.entity.Type;
import com.antologic.project.entity.User;
import com.antologic.project.exceptions.NotFoundException;
import com.antologic.project.forms.CreateProjectForm;
import com.antologic.project.mappers.ProjectMapper;
import com.antologic.project.repository.ProjectRepository;
import com.antologic.project.repository.UserRepository;
import com.antologic.project.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.antologic.project.utils.Util.calcBudget;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository,
                              ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectMapper = projectMapper;
    }

    public ProjectDto saveProject(CreateProjectForm createProjectForm) {
        return projectMapper.projectToProjectDto(projectRepository.save(projectMapper.createProjectFormToProject(createProjectForm)));
    }

    public boolean addToProject(String projectName, long userId) {
        Project project = projectRepository.findByName(projectName).orElseThrow(() -> new NotFoundException());
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException());

        if (user.getAccountType() != Type.EMPLOYEE) {
            return false;
        }

        project.addUser(user);
        projectRepository.save(project);
        return true;
    }

    public boolean removeFromProject(String projectName, String userLogin) {
        Project project = projectRepository.findByName(projectName).orElseThrow(() -> new NotFoundException());
        User user = userRepository.findByLogin(userLogin).orElseThrow(() -> new NotFoundException());
        project.removeUser(user);
        projectRepository.save(project);
        return true;
    }

    public void updateProject(CreateProjectForm createProjectForm, long projectId) {

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new NotFoundException());
        project.setName(createProjectForm.getName());
        project.setBudget(createProjectForm.getBudget());
        project.setBeginning(createProjectForm.getBeginning());
        project.setFinishing(createProjectForm.getFinishing());
        project.setDescription(createProjectForm.getDescription());
        projectRepository.save(project);
    }

    public boolean removeProject(String projectName) {
        projectRepository.delete(projectRepository.findByName(projectName).orElseThrow(() -> new NotFoundException()));
        return projectRepository.findByName(projectName) == null;
    }

    public List<ProjectMenagerDto> findAllWithBudget() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectMenagerDto> projectMenegerDtos = new ArrayList<>();
        projects.forEach(tempProject -> projectMenegerDtos.add(new ProjectMenagerDto(tempProject.getName(),
                tempProject.getUsers().stream().map(User::getLogin).collect(Collectors.toSet()),
                calcBudget(tempProject))));
        return projectMenegerDtos;
    }
}