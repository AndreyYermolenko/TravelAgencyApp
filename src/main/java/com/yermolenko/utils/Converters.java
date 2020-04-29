package com.yermolenko.utils;

import com.yermolenko.dto.TravelTourDtoForUser;
import com.yermolenko.forms.TravelTourForm;
import com.yermolenko.forms.UserForm;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@Builder
public class Converters {

    public static User from(UserForm userForm) {
        return User.builder()
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .build();
    }

    public static List<TravelTourDtoForUser> from(List<TravelTour> tours) {
        return tours.stream().map(Converters::from).collect(Collectors.toList());
    }

    private static TravelTourDtoForUser from(TravelTour tour) {
        return TravelTourDtoForUser.builder()
                .id(tour.getId())
                .destination(tour.getDestination())
                .beginDate(tour.getBeginDate())
                .endDate(tour.getEndDate())
                .cost(tour.getCost())
                .description(tour.getDescription())
                .build();
    }

    public static TravelTour from(TravelTourForm tourForm) {
        return TravelTour.builder()
                .destination(tourForm.getDestination())
                .beginDate(tourForm.getBeginDate())
                .endDate(tourForm.getEndDate())
                .cost(tourForm.getCost())
                .maxCount(tourForm.getMaxCount())
                .description(tourForm.getDescription())
                .build();
    }

}
