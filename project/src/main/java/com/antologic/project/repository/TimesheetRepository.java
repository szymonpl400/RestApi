package com.antologic.project.repository;

import com.antologic.project.entity.Timesheet;
import com.antologic.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {

    @Query("select t from Timesheet t where t.user = :user")
    List<Timesheet> findByUser(@Param("user") User user);

    @Query("select t from Timesheet t where t.uuid = :uuid")
    Optional<Timesheet> findByUuid(@Param("uuid")UUID timesheetUUID);
}
