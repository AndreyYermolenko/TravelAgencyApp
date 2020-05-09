package com.yermolenko.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class Resort.
 *
 * @author Andrey
 * Created on 09.05.2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resort {
    private int id;
    private String destination;
    private String country;
    private String description;
}
