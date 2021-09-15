package com.example.parking.services;

import com.example.parking.interfaces.IMainService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainService implements IMainService {

    public String showMenu () {
        return "home";
    }

    public String redirectHome () {
        return "redirect:/home";
    }

    /*public String staticResource() {
        return "staticResourceTest";
    }*/
}
