package com.antologic.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="userview")
public class UserView {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "login")
    private String login;

    @ManyToMany(mappedBy = "usersView", fetch = FetchType.LAZY)
    private Set<ProjectView> projectsView = new HashSet<>();
}
