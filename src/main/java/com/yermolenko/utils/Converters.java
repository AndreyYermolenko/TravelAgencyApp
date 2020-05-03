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

/**
 * Class Converters.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Data
@AllArgsConstructor
@Builder
public class Converters {

    /**
     * Method from converts UserForm to User.
     *
     * @param userForm of type UserForm
     * @return User
     */
    public static User from(UserForm userForm) {
        return User.builder()
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .build();
    }

    /**
     * Method from converts List<TravelTour> to List<TravelTourDtoForUser>.
     *
     * @param tours of type List<TravelTour>
     * @return List<TravelTourDtoForUser>
     */
    public static List<TravelTourDtoForUser> from(List<TravelTour> tours) {
        return tours.stream().map(Converters::from).collect(Collectors.toList());
    }

    /**
     * Method from converts TravelTour to TravelTourDtoForUser.
     *
     * @param tour of type TravelTour
     * @return TravelTourDtoForUser
     */
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

    /**
     * Method from converts TravelTourForm to TravelTour.
     *
     * @param tourForm of type TravelTourForm
     * @return TravelTour
     */
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
