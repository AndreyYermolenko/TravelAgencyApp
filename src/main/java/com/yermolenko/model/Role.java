package com.yermolenko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Class Role is a model.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    private int id;
    private String name;
}
