package com.yermolenko.dto;

import lombok.Data;

/**
 * Class TravelTourResortDto.
 *
 * @author Andrey
 * Created on 24.05.2020
 */
@Data
public class TravelTourResortDto {

    private String destinationResort;
    private String destinationTravelTour;
    private String destinationCarrier;
    private Float cost;

}
