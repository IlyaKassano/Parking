package com.example.parking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping(path="/home")
    public String showMenu (Map<String, Object> model) {
        return "home";
    }

    @GetMapping
    public String redirectHome (Map<String, Object> model) {
        return "redirect:/home";
    }

    //TODO @GetMapping(path="/error")

    @RequestMapping(value = "/staticResourceTest")
    public String staticResource(Model model) {
        return "staticResourceTest";
    }
}
