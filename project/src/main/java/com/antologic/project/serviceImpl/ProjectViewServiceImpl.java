package com.antologic.project.serviceImpl;

import com.antologic.project.dtos.ProjectViewDto;
import com.antologic.project.entity.ProjectView;
import com.antologic.project.forms.FilterProjectForm;
import com.antologic.project.mappers.ProjectViewMapper;
import com.antologic.project.repository.ProjectViewRepository;
import com.antologic.project.services.ProjectViewService;
import com.antologic.project.specification.ProjectViewSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectViewServiceImpl implements ProjectViewService {

    private final ProjectViewRepository projectViewRepository;
    private final ProjectViewMapper projectViewMapper;

    @Autowired
    public ProjectViewServiceImpl(ProjectViewRepository projectViewRepository, ProjectViewMapper projectViewMapper) {
        this.projectViewRepository = projectViewRepository;
        this.projectViewMapper = projectViewMapper;
    }

    @Override
    public List<ProjectViewDto> findWithFilter(FilterProjectForm search, Pageable pageable) {
        Specification<ProjectView> spec = new ProjectViewSpecification(search);
        return projectViewMapper.projectViewsToProjectViewsDto(projectViewRepository.findAll(spec, pageable).stream().collect(Collectors.toList()));
    }
}
