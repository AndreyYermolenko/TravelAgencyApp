package com.yermolenko.forms;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yermolenko.utils.LocalDateDeserializer;
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
