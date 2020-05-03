package com.yermolenko.dto;

import com.yermolenko.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class UserDto is a simple user dto class.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private int id;
    private String email;

    /**
     * Method from converts User to UserDto.
     *
     * @param user of type User
     * @return UserDto
     */
    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    /**
     * Method from converts List<User> to List<UserDto>.
     *
     * @param users of type List<User>
     * @return List<UserDto>
     */
    public static List<UserDto> from(List<User> users) {
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}
