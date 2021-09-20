package com.example.parking.controllers;

import com.example.parking.enums.ActionFront;
import com.example.parking.exception.InternalException;
import com.example.parking.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ClientController {
    private IClientService clientService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IClientService service) {
        this.clientService = service;
    }

    //Получить пустую форму
    @GetMapping(path="/clientAdd")
    public String getEmptyForm(Model model) {
        clientService.getForm(ActionFront.ADD, model);
        return "client/client";
    }

    //Обработка запроса на добавление новой записи
    @PostMapping(path="/clientAdd")
    public String addNewClient (@RequestParam String fio,
                                @RequestParam(value = "telephone") Optional<String> telephone) throws InternalException
    {
        clientService.addNewClient(fio, telephone);
        return "redirect:/clientAll";
    }

    //Получить форму всех записей для редактирования
    @GetMapping(path="/clientEdit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getFormEditClient(Model model) {
        clientService.findAllClient(ActionFront.EDIT, model);
        return "client/client";
    }

    //Получить форму для редактирования по айди
    @GetMapping(path="/clientEdit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getFormEditClientById(@PathVariable(value = "id") int idClient, Model model) {
        clientService.findClientById(idClient, ActionFront.EDIT, model);
        return "client/client";
    }

    //Обработка изменения данных из формы
    @PostMapping(path="/clientEdit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String putClientById(@RequestParam int idClient, @RequestParam String fio,
                                @RequestParam(value = "telephone") Optional<String> telephone, Model model)
            throws InternalException
    {
        clientService.editClientById(idClient, fio, telephone, model);
        return "redirect:/clientEdit";
    }

    //Изменение данных происходит при передачи айди по ссылке
    @PostMapping(path="/clientEdit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String putClientByPathId(@PathVariable(value = "id") int idClient, @RequestParam String fio,
                                    @RequestParam(value = "telephone") Optional<String> telephone, Model model)
            throws InternalException
    {
        clientService.editClientById(idClient, fio, telephone, model);
        return "redirect:/clientEdit";
    }

    //Получить форму всех записей на удаление
    @GetMapping(path="/clientDelete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllDeleteClient (Model model) {
        clientService.findAllClient(ActionFront.DELETE, model);
        return "client/client";
    }

    //Получить форму с заданным айди на удаление
    @GetMapping(path="/clientDelete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getToDeleteClient (@PathVariable(value = "id") int idClient, Model model) {
        clientService.findClientById(idClient, ActionFront.DELETE, model);
        return "client/client";
    }

    //Обработка удаления
    @PostMapping(path="/clientDelete" )
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteClientById(@RequestParam int idClient, Model model) {
        clientService.deleteClientById(idClient, model);
        return "client/client";
    }

    //Обработка удаления по айди
    @PostMapping(path="/clientDelete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteClientByPathId(@PathVariable(value = "id") int idClient, Model model) {
        clientService.deleteClientById(idClient, model);
        return "client/client";
    }

    //Получение формы всех записей
    @GetMapping(path="/clientAll")
    public String getAllClient (Model model) {
        clientService.findAllClient(ActionFront.ALL, model);
        return "client/client";
    }

    //Фильтрация данных на форме
    @PostMapping("/clientAll")
    public String filter (@RequestParam String fio, Model model)
    {
        clientService.getClientByFio(fio, ActionFront.ALL, model);
        return "client/client";
    }
}