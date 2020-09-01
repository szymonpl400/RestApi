package com.antologic.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    private String name;

    private String description;

    private LocalDate beginning;

    private LocalDate finishing;

    private BigDecimal budget;

    private Set<UserDto> users;
}
