package com.yermolenko.controllers;

import com.yermolenko.controllers.forms.LoginForm;
import com.yermolenko.controllers.forms.UserForm;
import com.yermolenko.model.User;
import com.yermolenko.services.RestService;
import com.yermolenko.services.OtherServices;
import com.yermolenko.services.UserService;
import com.yermolenko.utils.UserValidator;
import org.springframework.http.ResponseEntity;
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

    private final RestService restService;

    private final OtherServices otherServices;

    /**
     * Constructor AuthController creates a new AuthController instance.
     * @param userValidator of type UserValidator
     * @param userService of type UserService
     * @param restService
     * @param otherServices
     */
    public AuthController(UserValidator userValidator, UserService userService, RestService restService, OtherServices otherServices) {
        this.userValidator = userValidator;
        this.userService = userService;
        this.restService = restService;
        this.otherServices = otherServices;
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
        model.addAttribute("branches", otherServices.getAgencyBranches());

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

    /**
     * Controller login forms a request for api to receive a token.
     *
     * @param loginForm of type LoginForm
     * @return ResponseEntity<TokenDto>
     */
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(restService.login(loginForm).getValue());
    }

    /**
     * Controller addUser is responsible for adding a new user.
     *
     * @param userForm of type UserForm
     * @return ResponseEntity<Object>
     */
    @PostMapping("/api/sign_up")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        boolean result = restService.signUp(userForm);

        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
