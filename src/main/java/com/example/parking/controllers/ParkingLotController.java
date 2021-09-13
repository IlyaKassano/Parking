package com.example.parking.controllers;

import com.example.parking.entities.ParkingLotEntity;
import com.example.parking.repos.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class ParkingLotController {
    @Autowired
    private ParkingLotRepository lotsRepository;

    @GetMapping(path="/parkingLotAdd")
    public String showAllLots () {
        return "parkingLotAdd";
    }

    @PostMapping(path="/parkingLotAdd") // Map ONLY POST Requests
    public String addNewLots (@RequestParam String name, @RequestParam String address, @RequestParam int numLots, @RequestParam BigDecimal price) {
        //Вставка данных в БД
        //try {
            ParkingLotEntity lot = new ParkingLotEntity();
            lot.setName(name);
            lot.setAddress(address);
            lot.setNumLots(numLots);
            lot.setPrice(price);

            lotsRepository.save(lot);
        /*}
        catch(Exception e) {
            System.out.println(e.getMessage());
            //TODO Обработчик ошибок
            return "redirect:/error/addDbError";
        }*/

        return "redirect:/parkingLotAll";
    }

    @GetMapping(path="/parkingLotEdit")
    public String getToEditLot (Map<String, Object> model) {
        Iterable<ParkingLotEntity> lots = lotsRepository.findAll();
        model.put("lots", lots);
        return "parkingLotEdit";
    }

    @GetMapping(path="/parkingLotEdit/{id}")
    public String getToEditLot (@PathVariable(value = "id") int idLot, Map<String, Object> model) {
        List<ParkingLotEntity> lots = lotsRepository.findByIdLot(idLot);

        model.put("lots", lots);
        return "parkingLotEdit";
    }

    @PostMapping(path="/parkingLotEdit/{id}")
    public String editLot (@PathVariable(value = "id") int idLot, @RequestParam String name, @RequestParam String address, @RequestParam int numLots,
                           @RequestParam BigDecimal price, Map<String, Object> model) {
        ParkingLotEntity lot = lotsRepository.findById(idLot).orElseThrow();

        //Вставка данных в БД
        try {
            lot.setName(name);
            lot.setAddress(address);
            lot.setNumLots(numLots);
            lot.setPrice(price);

            lotsRepository.save(lot);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:/error/addDbError";
        }

        Iterable<ParkingLotEntity> lots = lotsRepository.findAll();
        model.put("lots", lots);
        return "parkingLotEdit";
    }

    @GetMapping(path="/parkingLotDelete")
    public String getAllDeleteLot (Map<String, Object> model) {
        Iterable<ParkingLotEntity> lots = lotsRepository.findAll();

        model.put("lots", lots);
        return "parkingLotDelete";
    }

    @GetMapping(path="/parkingLotDelete/{id}")
    public String getToDeleteLot (@PathVariable(value = "id") int idLot, Map<String, Object> model) {
        List<ParkingLotEntity> lots = lotsRepository.findByIdLot(idLot);

        model.put("lots", lots);
        return "parkingLotDelete";
    }

    @PostMapping(path="/parkingLotDelete" )
    public String deleteLot (@RequestParam int idLot, Map<String, Object> model) {
        ParkingLotEntity a = lotsRepository.findById(idLot).orElseThrow();
        lotsRepository.delete(a);

        Iterable<ParkingLotEntity> lots = lotsRepository.findAll();
        model.put("lots", lots);
        return "parkingLotDelete";
    }

    @PostMapping(path="/parkingLotDelete/{id}")
    public String deleteLotWithId (@RequestParam int idLot, Map<String, Object> model) {
        ParkingLotEntity a = lotsRepository.findById(idLot).orElseThrow();
        lotsRepository.delete(a);

        Iterable<ParkingLotEntity> lots = lotsRepository.findAll();
        model.put("lots", lots);
        return "parkingLotDelete";
    }

    @GetMapping(path="/parkingLotAll")
    public String getAllLot (Map<String, Object> model) {
        Iterable<ParkingLotEntity> lots = lotsRepository.findAll();

        model.put("lots", lots);
        return "parkingLotAll";
    }

    //FIXME parkingLotEdit/parkingLotEdit/filter
    @PostMapping("/parkingLotAll")
    public String filter (@RequestParam String name, Map<String, Object> model)
    {
        List<ParkingLotEntity> lots = lotsRepository.findByName(name);

        model.put("lots", lots);
        return "parkingLotAll";
    }
}