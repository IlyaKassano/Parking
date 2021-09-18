package com.example.parking.controllers;

import com.example.parking.interfaces.IAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class AutoController {
    private IAutoService autoService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IAutoService service) {
        this.autoService = service;
    }

    //Получить форму для добавления нового автомобиля
    @GetMapping(path="/autoAdd")
    public String getFormClientAdd() {
        return "auto/autoAdd";
    }

    //Обработка запроса на добавление нового автомобиля
    @PostMapping(path="/autoAdd")
    public String addNewAuto (@RequestParam String brand, @RequestParam String autoModel, HttpServletResponse response) {
        autoService.addNewAuto(brand, autoModel, response);
        return "redirect:/autoAll";
    }

    //Получить форму всех записей для редактирования
    @GetMapping(path="/autoEdit")
    public String getFormEditAuto(Model model) {
        autoService.findAllAuto(model);
        return "auto/autoEdit";
    }

    //Получить форму для редактирования нового автомобиля по айди
    @GetMapping(path="/autoEdit/{id}")
    public String getFormEditAutoById(@PathVariable(value = "id") int idAuto, Model model) {
        autoService.findAutoById(idAuto, model);
        return "auto/autoEdit";
    }

    //Изменение данных происходит при передачи айди по ссылке
    @PostMapping(path="/autoEdit")
    public String putAutoById(@RequestParam int idAuto, @RequestParam String brand,
                              @RequestParam String autoModel, Model model,
                              HttpServletResponse response) {
        autoService.editAutoById(idAuto, brand, autoModel, model, response);
        return "redirect:/autoEdit";
    }

    //Обработка изменения данных из формы
    @PostMapping(path="/autoEdit/{id}")
    public String putAutoByPathId(@PathVariable(value = "id") int idAuto, @RequestParam String brand,
                                  @RequestParam String autoModel, Model model,
                                  HttpServletResponse response) {
        autoService.editAutoById(idAuto, brand, autoModel, model, response);
        return "redirect:/autoEdit";
    }

    //Получить форму всех записей на удаление
    @GetMapping(path="/autoDelete")
    public String getAllDeleteAuto (Model model) {
        autoService.findAllAuto(model);
        return "auto/autoDelete";
    }

    //Получить форму с заданным айди на удаление
    @GetMapping(path="/autoDelete/{id}")
    public String getToDeleteAuto (@PathVariable(value = "id") int idAuto, Model model) {
        autoService.findAutoById(idAuto, model);
        return "auto/autoDelete";
    }

    //Обработка удаления
    @PostMapping(path="/autoDelete" )
    public String deleteAutoById(@RequestParam int idAuto, Model model) {
        autoService.deleteAutoById(idAuto, model);
        return "auto/autoDelete";
    }

    //Обработка удаления по айди
    @PostMapping(path="/autoDelete/{id}")
    public String deleteAutoByPathId(@PathVariable(value = "id") int idAuto, Model model) {
        autoService.deleteAutoById(idAuto, model);
        return "auto/autoDelete";
    }

    //Получение формы всех записей
    @GetMapping(path="/autoAll")
    public String getAllAuto (Model model) {
        autoService.findAllAuto(model);
        return "auto/autoAll";
    }

    //Фильтрация данных на форме
    @PostMapping("/autoAll")
    public String filter (@RequestParam String brand, @RequestParam String autoModel, Model model)
    {
        autoService.getAutoByBrandAndModel(brand, autoModel, model);
        return "auto/autoAll";
    }
}