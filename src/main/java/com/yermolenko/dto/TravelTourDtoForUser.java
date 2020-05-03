package com.yermolenko.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Class TravelTourDtoForUser is a simple travel tour dto for user class.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelTourDtoForUser {
    private int id;
    private String destination;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Float cost;
    private String description;
}
