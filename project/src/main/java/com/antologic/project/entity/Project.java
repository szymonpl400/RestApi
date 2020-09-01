package com.antologic.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project", schema = "public")
@PrimaryKeyJoinColumn(name = "id")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Project extends AbstractEntity {

    @Column(name = "name", unique = true, length = 255)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "beginning")
    private LocalDate beginning;

    @Column(name = "finishing")
    private LocalDate finishing;

    @Column(name = "budget")
    private BigDecimal budget;

    @JsonIgnore
    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Timesheet> timesheets = new HashSet<>();

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_project",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> users = new HashSet<>();

    public Project(String name, String description, LocalDate beginning, LocalDate finishing, BigDecimal budget) {
        this.name = name;
        this.description = description;
        this.beginning = beginning;
        this.finishing = finishing;
        this.budget = budget;
    }

    public void addUser(User user) {
        users.add(user);
        user.getProjects().add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.getProjects().remove(this);
    }

    public void addTimesheets(Timesheet timesheet) {
        timesheets.add(timesheet);
        timesheet.setProject(this);
    }

    public void removeTimesheets(Timesheet timesheet) {
        timesheets.remove(timesheet);
        timesheet.setProject(null);
    }
}