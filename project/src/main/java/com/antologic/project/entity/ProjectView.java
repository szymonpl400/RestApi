package com.antologic.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="projectview")
public class ProjectView {


    @Column(name = "pvid")
    private Long pvId;

    @Id
    @Column(name = "id")
    private Long projectId;

    @Column(name = "uuid")
    private UUID projectUUID;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "beginning")
    private LocalDate beginning;

    @Column(name = "finishing")
    private LocalDate finishing;

    @Column(name = "budget")
    private BigDecimal budget;

    @Column(name = "isexceeded")
    private boolean isExceeded;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "project_members_view",
            joinColumns = {@JoinColumn(name = "project_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<UserView> usersView = new HashSet<>();
}
