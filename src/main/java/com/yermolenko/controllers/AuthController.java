package com.yermolenko.controllers;

import com.yermolenko.model.User;
import com.yermolenko.services.UserService;
import com.yermolenko.utils.UserValidator;
import org.springframework.security.authentication.BadCredentialsException;
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

    /**
     * Constructor AuthController creates a new AuthController instance.
     *
     * @param userValidator of type UserValidator
     * @param userService of type UserService
     */
    public AuthController(UserValidator userValidator, UserService userService) {
        this.userValidator = userValidator;
        this.userService = userService;
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

        System.out.println(user.toString());
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
