package com.example.parking.controllers;

import com.example.parking.interfaces.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class ParkingController {
    private IParkingService parkingService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IParkingService service) {
        this.parkingService = service;
    }

    @GetMapping(path="/parkingAdd")
    public String showAllParking (Map<String, Object> model) {
        return parkingService.showAllParking(model);
    }

    @PostMapping(path="/parkingAdd")
    public String addNewParking (@RequestParam int idClient, @RequestParam int idAuto,
                                 @RequestParam int idLot, @RequestParam int lotItem,
                                 @RequestParam String dateParking, @RequestParam String dateDepart,
                                 @RequestParam(value = "paid", required = false) String paid,
                                 final HttpServletResponse response) {
        return parkingService.addNewParking(idClient, idAuto, idLot, lotItem, dateParking, dateDepart, paid, response);
    }

    @GetMapping(path="/parkingEdit")
    public String getToEditParking (Map<String, Object> model) {
        return parkingService.getToEditParking(model);
    }

    @GetMapping(path="/parkingEdit/{id}")
    public String getOneToEditParking (@PathVariable(value = "id") int idParking, Map<String, Object> model) {
        return parkingService.getOneToEditParking(idParking, model);
    }

    @PostMapping(path="/parkingEdit")
    public String editParking (@RequestParam int idParking, Map<String, Object> model) {
        return parkingService.editParking(idParking, model);
    }

    @PostMapping(path="/parkingEdit/{id}")
    public String editByIdParking (@PathVariable(value = "id") int idParking, @RequestParam int idClient,
                               @RequestParam int idAuto, @RequestParam int idLot, @RequestParam int lotItem,
                               @RequestParam String dateParking, @RequestParam String dateDepart,
                               @RequestParam(value = "paid", required = false) String paid,
                                   Map<String, Object> model, HttpServletResponse response)
    {
        return parkingService.editByIdParking(idParking, idClient, idAuto, idLot, lotItem, dateParking,
                dateDepart, paid, model, response);
    }

    @GetMapping(path="/parkingDelete")
    public String getAllDeleteParking (Map<String, Object> model) {
        return parkingService.getAllDeleteParking(model);
    }

   @GetMapping(path="/parkingDelete/{id}")
    public String getToDeleteParking (@PathVariable(value = "id") int idParking, Map<String, Object> model) {
       return parkingService.getToDeleteParking(idParking, model);
    }

    @PostMapping(path="/parkingDelete" )
    public String deleteParking (@RequestParam int idParking, Map<String, Object> model) {
        return parkingService.deleteParking(idParking, model);
    }

    @PostMapping(path="/parkingDelete/{id}")
    public String deleteParkingWithId (@RequestParam int idParking, Map<String, Object> model) {
        return parkingService.deleteParkingWithId(idParking, model);
    }

    @GetMapping(path="/parkingAll")
    public String getAllParking (Map<String, Object> model) {
        return parkingService.getAllParking(model);
    }

    @PostMapping("/parkingAll")
    public String filter (@RequestParam int idClient, @RequestParam int idAuto,
                          @RequestParam int idLot, Map<String, Object> model)
    {
        return parkingService.filter(idClient, idAuto, idLot, model);
    }
}