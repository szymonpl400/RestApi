package com.antologic.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "user", schema = "public")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class User extends AbstractEntity {

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private Type accountType;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "cost_per_hour")
    private BigDecimal costPerHour;

    @JsonIgnore
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Project> projects = new HashSet<>();

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Timesheet> timesheets = new HashSet<>();

    public User(String login, String firstName, String lastName, Type accountType, String password, String email, BigDecimal costPerHour) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountType = accountType;
        this.password = password;
        this.email = email;
        this.costPerHour = costPerHour;
    }

    public void addTimesheets(Timesheet timesheet) {
        timesheets.add(timesheet);
        timesheet.setUser(this);
    }

    public void removeTimesheets(Timesheet timesheet) {
        timesheets.remove(timesheet);
        timesheet.setUser(null);
    }
}

