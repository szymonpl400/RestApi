package com.antologic.project.forms;

import com.antologic.project.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FilterUserForm {
    private String login;

    private String firstName;

    private String lastName;

    private Type accountType;

    private BigDecimal costFrom;

    private BigDecimal costTo;

}
