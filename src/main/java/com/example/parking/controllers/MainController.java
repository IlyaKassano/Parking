package com.example.parking.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(path="/home")
    public String showMenu () {
        return "home";
    }

    @GetMapping
    public String redirectHome () {
        return "redirect:/home";
    }

    /*@RequestMapping(value = "/staticResourceTest")
    public String staticResource() {
        return clientService.staticResource();
    }*/
}
