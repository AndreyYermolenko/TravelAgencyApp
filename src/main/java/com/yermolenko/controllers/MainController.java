package com.yermolenko.controllers;

import com.yermolenko.dao.impl.UserDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserDAOImpl regularUserDAO;

    @GetMapping("/")
    public String view() {
        regularUserDAO.getAllTours();
        return "index";
    }

}
