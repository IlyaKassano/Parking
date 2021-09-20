package com.example.parking.controllers;

import com.example.parking.exception.InternalException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InternalErrorController {

    @ExceptionHandler(InternalException.class)
    public String handleException(InternalException e, Model model){
        model.addAttribute("code", e.getCode());
        model.addAttribute("message", e.getMessage());
        return "error";
    }

}
