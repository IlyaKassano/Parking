package com.example.parking.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController  implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer code = 0;
        String message = "Неизвестная ошибка!";


        if (status != null) {
            code = Integer.valueOf(status.toString());
        }

        if(code == HttpStatus.NOT_FOUND.value()) {
            message = "Страница не найдена!";
        }
        else if(code == HttpStatus.FORBIDDEN.value()) {
            message = "Нет доступа к данному ресурсу!";
        }
        //... прочие ошибки

        model.addAttribute("code", code);
        model.addAttribute("message", message);
        return "error";
    }

    public String getErrorPath() {
        return "error";
    }
}
