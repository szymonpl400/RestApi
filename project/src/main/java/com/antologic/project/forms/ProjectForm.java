package com.antologic.project.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectForm {

    @NotNull(message = "Name can't be null !")
    @Length(max = 255)
    private String name;

    @Length(max = 255)
    private String description;

    @NotNull(message = "Please provide beginning date")
    private LocalDate beginning;

    @NotNull(message = "Please provide ending date")
    private LocalDate finishing;

    @NotNull(message = "Please provide budget")
    @Min(1)
    private BigDecimal budget;

    private Set<UserForm> users;
}
