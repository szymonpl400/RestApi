package com.antologic.project.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectForm {

    @NotEmpty(message = "Name can't be null !")
    @Length(max = 255)
    private String name;

    @Length(max = 255)
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Please provide beginning date")
    private LocalDate beginning;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Please provide ending date")
    private LocalDate finishing;

    @NotNull(message = "Please provide budget")
    @Min(1)
    private BigDecimal budget;
}
