package com.antologic.project.repository;

import com.antologic.project.entity.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserViewRepository extends JpaRepository<UserView, Long> {

}
