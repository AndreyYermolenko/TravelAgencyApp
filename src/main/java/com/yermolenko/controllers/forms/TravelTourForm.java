package com.yermolenko.controllers.forms;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yermolenko.utils.LocalDateDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Class TravelTourForm is a form for data transfer.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TravelTourForm {
    private String destination;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    private Float cost;
    private Integer maxCount;
    private String description;
    private Integer travelCarrierId;
    private Integer resortId;
}
