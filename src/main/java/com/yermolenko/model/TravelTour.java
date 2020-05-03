package com.yermolenko.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Class TravelTour  is a model.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TravelTour {
    private int id;
    private String destination;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Float cost;
    private Integer maxCount;
    private Integer currentCount;
    private String description;
}
