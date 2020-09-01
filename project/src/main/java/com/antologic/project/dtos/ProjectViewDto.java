package com.antologic.project.dtos;

import com.antologic.project.entity.UserView;
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
public class ProjectViewDto {

    private Long projectId;

    private UUID projectUUID;

    private String name;

    private String description;

    private LocalDate beginning;

    private LocalDate finishing;

    private BigDecimal budget;

    private boolean isExceeded;

    private Set<UserViewDto> usersView = new HashSet<>();
}
