package com.antologic.project.repository;

import com.antologic.project.entity.ProjectView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectViewRepository extends JpaRepository<ProjectView, Long>, JpaSpecificationExecutor<ProjectView>{
    
}
