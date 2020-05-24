package com.yermolenko.dto;

import lombok.Data;

/**
 * Class BranchManagerDto.
 *
 * @author Andrey
 * Created on 24.05.2020
 */
@Data
public class BranchManagerDto {

    private String destinationBranch;
    private String lastNameManager;
    private Integer countOrder;

}
