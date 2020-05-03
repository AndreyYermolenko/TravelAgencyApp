package com.yermolenko.forms;

import lombok.Data;


/**
 * Class UserForm is a form for data transfer.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Data
public class UserForm {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
