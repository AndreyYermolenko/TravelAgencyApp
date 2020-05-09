package com.yermolenko.utils;

import com.yermolenko.controllers.forms.TravelTourForm;
import com.yermolenko.controllers.forms.UserForm;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

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
                .agencyBranchId(userForm.getAgencyBranchId())
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
                .travelCarrierId(tourForm.getTravelCarrierId())
                .resortId(tourForm.getResortId())
                .build();
    }

}
