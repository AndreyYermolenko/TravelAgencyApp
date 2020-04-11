package com.yermolenko.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int managerId;

}
