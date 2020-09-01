package com.antologic.project.forms;

import com.antologic.project.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserForm {

    @NotNull(message = "Login can't be null !")
    @Length(max = 255)
    private String login;

    @NotNull(message = "Firstname can't be null !")
    @Length(max = 255)
    private String firstName;

    @NotNull(message = "Lastname can't be null !")
    @Length(max = 255)
    private String lastName;

    @NotNull(message = "accountType can't be null !")
    private Type accountType;

    @NotNull(message = "Password can't be null !")
    @Length(max = 255)
    private String password;

    @Email(message = "Please provide valid Email")
    @NotNull(message = "Email can't be null !")
    @Length(max = 254)
    private String email;

    @NotNull
    @Min(1)
    private BigDecimal costPerHour;
}
