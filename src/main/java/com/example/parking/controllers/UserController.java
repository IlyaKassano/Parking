package com.example.parking.controllers;

import com.example.parking.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    private IUserService userService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IUserService service) {
        this.userService = service;
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String putRegistration(@RequestParam String username, @RequestParam String password, Model model,
                                  HttpServletResponse response){
        byte enabled = 1;
        if(userService.addNewUser(username, password, enabled, model, response))
            return "redirect:/login";
        else
            return "registration";
    }
}