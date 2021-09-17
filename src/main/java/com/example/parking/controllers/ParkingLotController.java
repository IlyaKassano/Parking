package com.example.parking.controllers;

import com.example.parking.interfaces.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@Controller
public class ParkingLotController {
    private IParkingLotService lotService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IParkingLotService service) {
        this.lotService = service;
    }

    @GetMapping(path="/parkingLotAdd")
    public String getFormClientAdd() {
        return "parkingLot/parkingLotAdd";
    }

    @PostMapping(path="/parkingLotAdd")
    public String addNewLot (@RequestParam String name, @RequestParam String address,
                             @RequestParam int numLots, @RequestParam BigDecimal price,
                             HttpServletResponse response) {
        lotService.addNewLot(name, address, numLots, price, response);
        return "redirect:/parkingLotAll";
    }

    @GetMapping(path="/parkingLotEdit")
    public String getFormEditLot(Model model) {
        lotService.findAllLot(model);
        return "parkingLot/parkingLotEdit";
    }

    @GetMapping(path="/parkingLotEdit/{id}")
    public String getFormEditLotById(@PathVariable(value = "id") int idLot, Model model) {
        lotService.findLotById(idLot, model);
        return "parkingLot/parkingLotEdit";
    }

    @PostMapping(path="/parkingLotEdit")
    public String putLotById(@RequestParam int idLot) {
        return "redirect:/parkingLotEdit/" + idLot;
    }

    @PostMapping(path="/parkingLotEdit/{id}")
    public String putLotByPathId(@PathVariable(value = "id") int idLot, @RequestParam String name,
                                 @RequestParam String address, @RequestParam int numLots,
                                 @RequestParam BigDecimal price, Model model,
                                 HttpServletResponse response) {
        lotService.editLotById(idLot, name, address, numLots, price, model, response);
        return "redirect:/parkingLotEdit";
    }

    @GetMapping(path="/parkingLotDelete")
    public String getAllDeleteLot (Model model) {
        lotService.findAllLot(model);
        return "parkingLot/parkingLotDelete";
    }

    @GetMapping(path="/parkingLotDelete/{id}")
    public String getToDeleteLot (@PathVariable(value = "id") int idLot, Model model) {
        lotService.findLotById(idLot, model);
        return "parkingLot/parkingLotDelete";
    }

    @PostMapping(path="/parkingLotDelete" )
    public String deleteLotById(@RequestParam int idLot, Model model) {
        lotService.deleteLotById(idLot, model);
        return "parkingLot/parkingLotDelete";
    }

    @PostMapping(path="/parkingLotDelete/{id}")
    public String deleteLotByPathId(@PathVariable(value = "id") int idLot, Model model) {
        lotService.deleteLotById(idLot, model);
        return "parkingLot/parkingLotDelete";
    }

    @GetMapping(path="/parkingLotAll")
    public String getAllLot (Model model) {
        lotService.findAllLot(model);
        return "parkingLot/parkingLotAll";
    }

    @PostMapping("/parkingLotAll")
    public String filter (@RequestParam String name, Model model)
    {
        lotService.getLotByName(name, model);
        return "parkingLot/parkingLotAll";
    }
}