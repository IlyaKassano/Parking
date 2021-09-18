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
    private IParkingService parkingService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IParkingService service) {
        this.parkingService = service;
    }

    @GetMapping(path="/parkingAdd")
    public String getFormParkingAdd(Model model) {
        parkingService.findAllParking(model);
        return "parking/parkingAdd";
    }

    @PostMapping(path="/parkingAdd")
    public String addNewParking (@RequestParam int idClient, @RequestParam int idAuto,
                             @RequestParam int idLot, @RequestParam int lotItem,
                             @RequestParam String dateParking, @RequestParam String dateDepart,
                             @RequestParam(value = "paid", required = false) String paid,
                             Model model, final HttpServletResponse response) {

        if (parkingService.checkValidLotItem(idLot, lotItem)) {
            parkingService.addNewParking(idClient, idAuto, idLot, lotItem, dateParking, dateDepart, paid, response);
            return "redirect:/parkingAll";
        }
        else {
            model.addAttribute("error", "Данное парковочное место уже занято, " +
                    "либо его номер превышает допустимый у данной парковки! Попробуйте другое.");
            parkingService.findAllParking(model);
            return "parking/parkingAdd";
        }
    }

    @GetMapping(path="/parkingEdit")
    public String getFormEditParking(Model model) {
        parkingService.findAllParking(model);
        return "parking/parkingEdit";
    }

    @GetMapping(path="/parkingEdit/{id}")
    public String getFormEditParkingById(@PathVariable(value = "id") int idParking, Model model) {
        parkingService.findParkingById(idParking, model);
        return "parking/parkingEdit";
    }

    //FIXME 404 Не дебажится
    @PostMapping(path="/parkingEdit")
    public String putParkingById(@RequestParam int idParking, @RequestParam int idClient,
                                 @RequestParam int idAuto, @RequestParam int idLot, @RequestParam int lotItem,
                                 @RequestParam String dateParking, @RequestParam String dateDepart,
                                 @RequestParam(value = "paid", required = false) String paid,
                                 Model model, HttpServletResponse response)
    {
        parkingService.editParkingById(idParking, idClient, idAuto, idLot, lotItem, dateParking,
                dateDepart, paid, model, response);
        return "parking/parkingEdit";
    }

    @PostMapping(path="/parkingEdit/{id}")
    public String putParkingByPathId(@PathVariable(value = "id") int idParking, @RequestParam int idClient,
                                     @RequestParam int idAuto, @RequestParam int idLot, @RequestParam int lotItem,
                                     @RequestParam String dateParking, @RequestParam String dateDepart,
                                     @RequestParam(value = "paid", required = false) String paid,
                                     Model model, HttpServletResponse response)
    {
        parkingService.editParkingById(idParking, idClient, idAuto, idLot, lotItem, dateParking,
                dateDepart, paid, model, response);
        return "redirect:/parkingEdit";
    }

    @GetMapping(path="/parkingDelete")
    public String getAllDeleteParking (Model model) {
        parkingService.findAllParking(model);
        return "parking/parkingDelete";
    }

    @GetMapping(path="/parkingDelete/{id}")
    public String getToDeleteParking (@PathVariable(value = "id") int idParking, Model model) {
        parkingService.findParkingById(idParking, model);
        return "parking/parkingDelete";
    }

    @PostMapping(path="/parkingDelete" )
    public String deleteParkingById(@RequestParam int idParking, Model model) {
        parkingService.deleteParkingById(idParking, model);
        return "parking/parkingDelete";
    }

    @PostMapping(path="/parkingDelete/{id}")
    public String deleteParkingByPathId(@PathVariable(value = "id") int idParking, Model model) {
        parkingService.deleteParkingById(idParking, model);
        return "parking/parkingDelete";
    }

    @GetMapping(path="/parkingAll")
    public String getAllParking (Model model) {
        parkingService.findAllParking(model);
        return "parking/parkingAll";
    }

    @PostMapping("/parkingAll")
    public String filter (@RequestParam int idClient, @RequestParam int idAuto,
                          @RequestParam int idLot, Model model)
    {
        parkingService.getParkingByPrimaryKeys(idClient, idAuto, idLot, model);
        return "parking/parkingAll";
    }
}