package com.yermolenko.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class TravelCarrier.
 *
 * @author Andrey
 * Created on 09.05.2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelCarrier {
    private int id;
    private String destination;
}
