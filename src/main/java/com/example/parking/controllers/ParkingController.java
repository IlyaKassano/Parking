package com.example.parking.controllers;

import com.example.parking.interfaces.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ParkingController {
    private IParkingService lotService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IParkingService service) {
        this.lotService = service;
    }

    @GetMapping(path="/parkingAdd")
    public String getFormParkingAdd() {
        return "parkingAdd";
    }

    @PostMapping(path="/parkingAdd")
    public String addNewParking (@RequestParam int idClient, @RequestParam int idAuto,
                             @RequestParam int idLot, @RequestParam int lotItem,
                             @RequestParam String dateParking, @RequestParam String dateDepart,
                             @RequestParam(value = "paid", required = false) String paid,
                             final HttpServletResponse response) {
        lotService.addNewParking(idClient, idAuto, idLot, lotItem, dateParking, dateDepart, paid, response);
        return "redirect:/parkingAll";
    }

    @GetMapping(path="/parkingEdit")
    public String getFormEditParking() {
        return "redirect:/parkingAll";
    }

    @GetMapping(path="/parkingEdit/{id}")
    public String getFormEditParkingById(@PathVariable(value = "id") int idParking, Model model) {
        lotService.findParkingById(idParking, model);
        return "parkingEdit";
    }

    @PostMapping(path="/parkingEdit")
    public String putParkingById(@RequestParam(value = "idParking") int idParking) {
        return "redirect:/parkingEdit/" + idParking;
    }

    @PostMapping(path="/parkingEdit/{id}")
    public String putParkingByPathId(@PathVariable(value = "id") int idParking, @RequestParam int idClient,
                                     @RequestParam int idAuto, @RequestParam int idLot, @RequestParam int lotItem,
                                     @RequestParam String dateParking, @RequestParam String dateDepart,
                                     @RequestParam(value = "paid", required = false) String paid,
                                     Model model, HttpServletResponse response) {
        lotService.editParkingById(idParking, idClient, idAuto, idLot, lotItem, dateParking,
                dateDepart, paid, model, response);
        return "redirect:/parkingEdit";
    }

    @GetMapping(path="/parkingDelete")
    public String getAllDeleteParking (Model model) {
        lotService.findAllParking(model);
        return "parkingDelete";
    }

    @GetMapping(path="/parkingDelete/{id}")
    public String getToDeleteParking (@PathVariable(value = "id") int idParking, Model model) {
        lotService.findParkingById(idParking, model);
        return "parkingDelete";
    }

    @PostMapping(path="/parkingDelete" )
    public String deleteParkingById(@RequestParam int idParking, Model model) {
        lotService.deleteParkingById(idParking, model);
        return "parkingDelete";
    }

    @PostMapping(path="/parkingDelete/{id}")
    public String deleteParkingByPathId(@PathVariable(value = "id") int idParking, Model model) {
        lotService.deleteParkingById(idParking, model);
        return "parkingDelete";
    }

    @GetMapping(path="/parkingAll")
    public String getAllParking (Model model) {
        lotService.findAllParking(model);
        return "parkingAll";
    }

    @PostMapping("/parkingAll")
    public String filter (@RequestParam int idClient, @RequestParam int idAuto,
                          @RequestParam int idLot, Model model)
    {
        lotService.getParkingByPrimaryCodes(idClient, idAuto, idLot, model);
        return "parkingAll";
    }
}