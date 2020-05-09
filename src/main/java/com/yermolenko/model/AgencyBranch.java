package com.yermolenko.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class AgencyBranch.
 *
 * @author Andrey
 * Created on 09.05.2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgencyBranch {
    private int id;
    private String destination;
    private String address;
}
