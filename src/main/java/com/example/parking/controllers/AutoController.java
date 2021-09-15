package com.example.parking.controllers;

import com.example.parking.interfaces.IAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class AutoController {
    private IAutoService autoService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IAutoService service) {
        this.autoService = service;
    }

    @GetMapping(path="/autoAdd")
    public String showAllAutos () {
        return autoService.showAllAutos();
    }

    @PostMapping(path="/autoAdd")
    public String addNewAuto (@RequestParam String brand, @RequestParam String autoModel, HttpServletResponse response) {
        return autoService.addNewAuto(brand, autoModel, response);
    }

    @GetMapping(path="/autoEdit")
    public String getToEditAuto (Map<String, Object> model) {
        return autoService.getToEditAuto(model);
    }

    @GetMapping(path="/autoEdit/{id}")
    public String getOneToEditAuto (@PathVariable(value = "id") int idAuto, Map<String, Object> model) {
        return autoService.getOneToEditAuto(idAuto, model);
    }

    @PostMapping(path="/autoEdit")
    public String editAuto (@RequestParam int idAuto, Map<String, Object> model) {
        return autoService.editAuto(idAuto, model);
    }

    @PostMapping(path="/autoEdit/{id}")
    public String editByIdAuto (@PathVariable(value = "id") int idAuto, @RequestParam String brand,
                                @RequestParam String autoModel, Map<String, Object> model,
                                HttpServletResponse response) {
        return autoService.editByIdAuto(idAuto, brand, autoModel, model, response);
    }

    @GetMapping(path="/autoDelete")
    public String getAllDeleteAuto (Map<String, Object> model) {
        return autoService.getAllDeleteAuto(model);
    }

    @GetMapping(path="/autoDelete/{id}")
    public String getToDeleteAuto (@PathVariable(value = "id") int idAuto, Map<String, Object> model) {
        return autoService.getToDeleteAuto(idAuto, model);
    }

    @PostMapping(path="/autoDelete" )
    public String deleteAuto (@RequestParam int idAuto, Map<String, Object> model) {
        return autoService.deleteAuto(idAuto, model);
    }

    @PostMapping(path="/autoDelete/{id}")
    public String deleteAutoWithId (@RequestParam int idAuto, Map<String, Object> model) {
        return autoService.deleteAutoWithId(idAuto, model);
    }

    @GetMapping(path="/autoAll")
    public String getAllAuto (Map<String, Object> model) {
        return autoService.getAllAuto(model);
    }

    @PostMapping("/autoAll")
    public String filter (@RequestParam String brand, @RequestParam String autoModel, Map<String, Object> model)
    {
        return autoService.filter(brand, autoModel, model);
    }
}