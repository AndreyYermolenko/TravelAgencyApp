package com.yermolenko.utils;

import com.yermolenko.dao.UserDAO;
import com.yermolenko.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Class UserValidator.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Component
public class UserValidator implements Validator {

    private final UserDAO userDAO;

    /**
     * Constructor UserValidator creates a new UserValidator instance.
     *
     * @param userDAO of type UserDAO
     */
    public UserValidator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Method supports.
     *
     * @param aClass of type Class<?>
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * Method validate.
     *
     * @param o of type Object
     * @param errors of type Errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userDAO.findUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "", "This email is already use.");
        }
    }
}

