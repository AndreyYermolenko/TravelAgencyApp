package com.yermolenko.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Token  is a model.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token {

    private int id;
    private String value;
    private User user;
}
