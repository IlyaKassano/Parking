package com.example.parking.controllers;

import com.example.parking.entities.UserEntity;
import com.example.parking.interfaces.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    private IRegistrationService userService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IRegistrationService service) {
        this.userService = service;
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String putRegistration(@RequestParam String username, @RequestParam String password, Model model){
        if(userService.putRegistration(username, password, model))
            return "redirect:/login";
        else
            return "registration";
    }
}
