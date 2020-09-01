package com.antologic.project.services;

import com.antologic.project.dtos.ProjectViewDto;
import com.antologic.project.forms.FilterProjectForm;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectViewService {
    List<ProjectViewDto> findWithFilter(FilterProjectForm search, Pageable pageable);

}
