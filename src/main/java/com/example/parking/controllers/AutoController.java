package com.example.parking.controllers;

import com.example.parking.enums.ActionFront;
import com.example.parking.interfaces.IAutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    //Получить пустую форму
    @GetMapping(path="/autoAdd")
    public String getEmptyForm(Model model) {
        autoService.getForm(ActionFront.ADD, model);
        return "auto/auto";
    }

    //Обработка запроса на добавление новой записи
    @PostMapping(path="/autoAdd")
    public String addNewAuto (@RequestParam String brand, @RequestParam String autoModel, HttpServletResponse response) {
        autoService.addNewAuto(brand, autoModel, response);
        return "redirect:/autoAll";
    }

    //Получить форму всех записей для редактирования
    @GetMapping(path="/autoEdit")
    public String getFormEditAuto(Model model) {
        autoService.findAllAuto(ActionFront.EDIT, model);
        return "auto/auto";
    }

    //Получить форму для редактирования по айди
    @GetMapping(path="/autoEdit/{id}")
    public String getFormEditAutoById(@PathVariable(value = "id") int idAuto, Model model) {
        autoService.findAutoById(idAuto, ActionFront.EDIT, model);
        return "auto/auto";
    }

    //Обработка изменения данных из формы
    @PostMapping(path="/autoEdit")
    public String putAutoById(@RequestParam int idAuto, @RequestParam String brand,
                              @RequestParam String autoModel, Model model,
                              HttpServletResponse response) {
        autoService.editAutoById(idAuto, brand, autoModel, model, response);
        return "redirect:/autoEdit";
    }

    //Изменение данных происходит при передачи айди по ссылке
    @PostMapping(path="/autoEdit/{id}")
    public String putAutoByPathId(@PathVariable(value = "id") int idAuto, @RequestParam String brand,
                                  @RequestParam String autoModel, Model model,
                                  HttpServletResponse response) {
        autoService.editAutoById(idAuto, brand, autoModel, model, response);
        return "redirect:/autoEdit";
    }

    //Получить форму всех записей на удаление
    @GetMapping(path="/autoDelete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllDeleteAuto (Model model) {
        autoService.findAllAuto(ActionFront.DELETE, model);
        return "auto/auto";
    }

    //Получить форму с заданным айди на удаление
    @GetMapping(path="/autoDelete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getToDeleteAuto (@PathVariable(value = "id") int idAuto, Model model) {
        autoService.findAutoById(idAuto, ActionFront.DELETE, model);
        return "auto/auto";
    }

    //Обработка удаления
    @PostMapping(path="/autoDelete" )
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteAutoById(@RequestParam int idAuto, Model model) {
        autoService.deleteAutoById(idAuto, model);
        return "auto/auto";
    }

    //Обработка удаления по айди
    @PostMapping(path="/autoDelete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteAutoByPathId(@PathVariable(value = "id") int idAuto, Model model) {
        autoService.deleteAutoById(idAuto, model);
        return "auto/auto";
    }

    //Получение формы всех записей
    @GetMapping(path="/autoAll")
    public String getAllAuto (Model model) {
        autoService.findAllAuto(ActionFront.ALL, model);
        return "auto/auto";
    }

    //Фильтрация данных на форме
    @PostMapping("/autoAll")
    public String filter (@RequestParam String brand, @RequestParam String autoModel, Model model)
    {
        autoService.getAutoByBrandAndModel(brand, autoModel, ActionFront.ALL, model);
        return "auto/auto";
    }
}