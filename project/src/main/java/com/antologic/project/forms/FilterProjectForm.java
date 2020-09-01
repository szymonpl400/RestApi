package com.antologic.project.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilterProjectForm {

    private String name;

    private LocalDate beginning;

    private LocalDate finishing;

    private List<String> users;

    private Boolean budgetExpenses;
}
