package com.antologic.project.repository;

import com.antologic.project.entity.User;
import com.antologic.project.forms.FilterUserForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<FilterUserForm> {
    @Query("Select u from User u " +
            "left join fetch u.projects p " +
            "left join fetch p.timesheets " +
            "where u.login = :login")
    Optional<User> findByLogin(@Param("login")String login);
}
