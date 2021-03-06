package com.yermolenko.model;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Class User is a model.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
    private int id;

    @NotBlank(message = "Email is required!")
    @Email(message = "Incorrect email!")
    private String email;

    @Size(min = 4, message = "Password should be more 4 symbols!")
    private String password;

    @Size(min = 1, max = 10, message = "First name should be from 1 to 10 symbols!")
    private String firstName;

    @Size(min = 1, max = 10, message = "Last name should be from 1 to 10 symbols!")
    private String lastName;

    private Integer managerId;

    private Set<Role> roles;

    private Integer agencyBranchId;

}
