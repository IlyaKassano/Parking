package com.example.parking.controllers;

import com.example.parking.interfaces.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;

@Controller
public class ParkingLotController {
    private IParkingLotService lotService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IParkingLotService service) {
        this.lotService = service;
    }

    public String showAllLots () {
        return lotService.showAllLots();
    }

    public String addNewLots (@RequestParam String name, @RequestParam String address,
                              @RequestParam int numLots, @RequestParam BigDecimal price,
                              HttpServletResponse response) {
        return lotService.addNewLot(name, address, numLots, price, response);
    }

    @GetMapping(path="/parkingLotEdit")
    public String getToEditLot (Map<String, Object> model) {
        return lotService.getToEditLot(model);
    }

    @GetMapping(path="/parkingLotEdit/{id}")
    public String getOneToEditLot (@PathVariable(value = "id") int idLot, Map<String, Object> model) {
        return lotService.getOneToEditLot(idLot, model);
    }

    @PostMapping(path="/parkingLotEdit")
    public String editLot (@RequestParam int idLot, Map<String, Object> model) {
        return lotService.editLot(idLot, model);
    }

    @PostMapping(path="/parkingLotEdit/{id}")
    public String editByIdLot (@PathVariable(value = "id") int idLot, @RequestParam String name,
                               @RequestParam String address, @RequestParam int numLots,
                           @RequestParam BigDecimal price, Map<String, Object> model,
                               HttpServletResponse response) {
        return lotService.editByIdLot(idLot, name, address, numLots, price, model, response);
    }

    @GetMapping(path="/parkingLotDelete")
    public String getAllDeleteLot (Map<String, Object> model) {
        return lotService.getAllDeleteLot(model);
    }

    @GetMapping(path="/parkingLotDelete/{id}")
    public String getToDeleteLot (@PathVariable(value = "id") int idLot, Map<String, Object> model) {
        return lotService.getToDeleteLot(idLot, model);
    }

    @PostMapping(path="/parkingLotDelete" )
    public String deleteLot (@RequestParam int idLot, Map<String, Object> model) {
        return lotService.deleteLot(idLot, model);
    }

    @PostMapping(path="/parkingLotDelete/{id}")
    public String deleteLotWithId (@RequestParam int idLot, Map<String, Object> model) {
        return lotService.deleteLotWithId(idLot, model);
    }

    @GetMapping(path="/parkingLotAll")
    public String getAllLot (Map<String, Object> model) {
        return lotService.getAllLot(model);
    }

    @PostMapping("/parkingLotAll")
    public String filter (@RequestParam String name, Map<String, Object> model)
    {
        return lotService.filter(name, model);
    }
}