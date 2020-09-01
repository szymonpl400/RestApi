package com.antologic.project.repository;

import com.antologic.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("Select p from Project p " +
            "left join fetch p.users u " +
            "left join fetch u.timesheets " +
            "where p.name = :name")
    Optional<Project> findByName(@Param("name") String name);
}
