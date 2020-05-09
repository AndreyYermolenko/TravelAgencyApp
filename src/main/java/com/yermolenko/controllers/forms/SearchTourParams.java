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
 * Class SearchTourParams is a form for data transfer.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchTourParams {
    private String destination;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate beginDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Float minCost;
    private Float maxCost;
    private String sortedBy;
    private Boolean desc = false;
}
