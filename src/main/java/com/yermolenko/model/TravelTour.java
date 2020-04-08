package com.yermolenko.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TravelTour {
    private int id;
    private String destination;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate beginDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;
    private float cost;
    private int maxCount;
    private int currentCount;
    private String description;

}
