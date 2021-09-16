package com.example.parking.controllers;

import com.example.parking.interfaces.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class ClientController {
    private IClientService clientService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IClientService service) {
        this.clientService = service;
    }

    @GetMapping(path="/clientAdd")
    public String getFormClientAdd() {
        return "clientAdd";
    }

    @PostMapping(path="/clientAdd")
    public String addNewClient (@RequestParam String fio, @RequestParam(value = "telephone") Optional<String> telephone, HttpServletResponse response) {
        clientService.addNewClient(fio, telephone, response);
        return "redirect:/clientAll";
    }

    @GetMapping(path="/clientEdit")
    public String getFormEditClient(Model model) {
        clientService.findAllClient(model);
        return "clientEdit";
    }

    @GetMapping(path="/clientEdit/{id}")
    public String getFormEditClientById(@PathVariable(value = "id") int idClient, Model model) {
        clientService.findClientById(idClient, model);
        return "clientEdit";
    }

    @PostMapping(path="/clientEdit")
    public String putClientById(@RequestParam int idClient) {
        return "redirect:/clientEdit/" + idClient;
    }

    @PostMapping(path="/clientEdit/{id}")
    public String putClientByPathId(@PathVariable(value = "id") int idClient, @RequestParam String fio,
                                    @RequestParam(value = "telephone") Optional<String> telephone, Model model,
                                    HttpServletResponse response) {
        clientService.editClientById(idClient, fio, telephone, model, response);
        return "redirect:/clientEdit";
    }

    @GetMapping(path="/clientDelete")
    public String getAllDeleteClient (Model model) {
        clientService.findAllClient(model);
        return "clientDelete";
    }

    @GetMapping(path="/clientDelete/{id}")
    public String getToDeleteClient (@PathVariable(value = "id") int idClient, Model model) {
        clientService.findClientById(idClient, model);
        return "clientDelete";
    }

    @PostMapping(path="/clientDelete" )
    public String deleteClientById(@RequestParam int idClient, Model model) {
        clientService.deleteClientById(idClient, model);
        return "clientDelete";
    }

    @PostMapping(path="/clientDelete/{id}")
    public String deleteClientByPathId(@PathVariable(value = "id") int idClient, Model model) {
        clientService.deleteClientById(idClient, model);
        return "clientDelete";
    }

    @GetMapping(path="/clientAll")
    public String getAllClient (Model model) {
        clientService.findAllClient(model);
        return "clientAll";
    }

    @PostMapping("/clientAll")
    public String filter (@RequestParam String fio, Model model)
    {
        clientService.getClientByFio(fio, model);
        return "clientAll";
    }
}