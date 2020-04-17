package com.yermolenko.controllers;

import com.yermolenko.model.User;
import com.yermolenko.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign_up")
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "auth/sign_up";
    }

    @PostMapping("/sign_up")
    public String registration(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "auth/sign_up";
        }

        System.out.println(user.toString());
        userService.registrationUser(user);

        return "redirect:login";
    }

    @RequestMapping(value = {"/login", "/"})
    public String view() {
        return "auth/sign_in";
    }

}
