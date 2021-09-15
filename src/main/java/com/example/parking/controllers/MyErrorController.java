package com.example.parking.controllers;

import com.example.parking.interfaces.IErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class MyErrorController {
    private IErrorService errorService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IErrorService service) {
        this.errorService = service;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Map<String, Object> model) {
        return errorService.handleError(request, model);
    }
}
