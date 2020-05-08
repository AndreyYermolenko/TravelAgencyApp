package com.yermolenko.controllers;

import com.yermolenko.model.User;
import com.yermolenko.services.TravelAgencyService;
import com.yermolenko.services.UserService;
import com.yermolenko.utils.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Class AuthController contains authentication controllers.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Controller
public class AuthController {

    private final UserValidator userValidator;

    private final UserService userService;

    private final TravelAgencyService travelAgencyService;

    /**
     * Constructor AuthController creates a new AuthController instance.
     * @param userValidator of type UserValidator
     * @param userService of type UserService
     * @param travelAgencyService
     */
    public AuthController(UserValidator userValidator, UserService userService, TravelAgencyService travelAgencyService) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.travelAgencyService = travelAgencyService;
    }

    /**
     * Controller registration is responsible for registration users.
     *
     * @param model of type Model
     * @return String
     */
    @GetMapping("/sign_up")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("branches", travelAgencyService.getAgencyBranches());

        return "/auth/sign_up";
    }

    /**
     * Controller registration is responsible for registration users.
     *
     * @param user of type User
     * @param result of type BindingResult
     * @return String
     */
    @PostMapping("/sign_up")
    public String registration(@ModelAttribute @Valid User user, BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "/auth/sign_up";
        }

        userService.registrationUser(user);

        return "redirect:/login";
    }

    /**
     * Controller login is responsible for sign in.
     * @return String
     */
    @RequestMapping(value = {"/login", "/"})
    public String login(@RequestParam(name = "error", required = false) Boolean error,
                        Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        return "/auth/sign_in";
    }

}
