package com.antologic.project.dtos;

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
public class UserDto {

    private String login;

    private String firstName;

    private String lastName;

    private Type accountType;

    private String email;

    private BigDecimal costPerHour;
}

