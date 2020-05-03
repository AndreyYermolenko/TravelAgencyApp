package com.yermolenko.dto;

import com.yermolenko.model.Token;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class TokenDto is a simple token dto class.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Data
@AllArgsConstructor
public class TokenDto {
    private String value;

    /**
     * Method from converts Token to TokenDto.
     *
     * @param token of type Token
     * @return TokenDto
     */
    public static TokenDto from(Token token) {
        return new TokenDto(token.getValue());
    }

}
