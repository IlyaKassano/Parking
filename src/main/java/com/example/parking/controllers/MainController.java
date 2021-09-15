package com.example.parking.controllers;

import com.example.parking.interfaces.IMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private IMainService mainService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IMainService service) {
        this.mainService = service;
    }

    @GetMapping(path="/home")
    public String showMenu () {
        return mainService.showMenu();
    }

    @GetMapping
    public String redirectHome () {
        return mainService.redirectHome();
    }

    /*@RequestMapping(value = "/staticResourceTest")
    public String staticResource() {
        return clientService.staticResource();
    }*/
}
