package com.example.parking.controllers;

import com.example.parking.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Controller
public class ClientController {
    private IClientService clientService;

    //Разворачивание сервиса
    @Autowired
    public void setMessageService(IClientService service) {
        this.clientService = service;
    }

    @GetMapping(path="/clientAdd")
    public String showAllClients() {
        return clientService.showAllClients();
    }

    @PostMapping(path="/clientAdd")
    public String addNewClient (@RequestParam String fio, @RequestParam(value = "telephone") Optional<String> telephone,
                                HttpServletResponse response) {
        return clientService.addNewClient(fio, telephone, response);
    }

    @GetMapping(path="/clientEdit")
    public String getToEditClient (Map<String, Object> model) {
        return clientService.getToEditClient(model);
    }

    @GetMapping(path="/clientEdit/{id}")
    public String getOneToEditClient (@PathVariable(value = "id") int idClient, Map<String, Object> model) {
        return clientService.getOneToEditClient(idClient, model);
    }

    @PostMapping(path="/clientEdit")
    public String editClient (@RequestParam int idClient, Map<String, Object> model) {
        return clientService.editClient(idClient, model);
    }

    @PostMapping(path="/clientEdit/{id}")
    public String editByIdClient (@PathVariable(value = "id") int idClient, @RequestParam String fio,
                                @RequestParam(value = "telephone") Optional<String> telephone,
                                  Map<String, Object> model, HttpServletResponse response) {
        return clientService.editByIdClient(idClient, fio, telephone, model, response);
    }

    @GetMapping(path="/clientDelete")
    public String getAllDeleteClient (Map<String, Object> model) {
        return clientService.getAllDeleteClient(model);
    }

    @GetMapping(path="/clientDelete/{id}")
    public String getToDeleteClient (@PathVariable(value = "id") int idClient, Map<String, Object> model) {
        return clientService.getToDeleteClient(idClient, model);
    }

    @PostMapping(path="/clientDelete")
    public String deleteClient (@RequestParam int idClient, Map<String, Object> model) {
        return clientService.deleteClient(idClient, model);
    }

    @PostMapping(path="/clientDelete/{id}")
    public String deleteClientWithId (@RequestParam int idClient, Map<String, Object> model) {
        return clientService.deleteClientWithId(idClient, model);
    }

    @GetMapping(path="/clientAll")
    public String getAllClient (Map<String, Object> model) {
        return clientService.getAllClient(model);
    }

    @PostMapping("clientAll")
    public String filter (@RequestParam String fio, Map<String, Object> model)
    {
        return clientService.filter(fio, model);
    }
}