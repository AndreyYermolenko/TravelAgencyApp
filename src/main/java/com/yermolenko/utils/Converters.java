package com.yermolenko.utils;

import com.yermolenko.forms.UserForm;
import com.yermolenko.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Converters {

    public static User convertUserFormToUser(UserForm userForm) {
        return User.builder()
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .build();
    }

}
