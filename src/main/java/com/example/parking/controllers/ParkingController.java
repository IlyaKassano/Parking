package com.example.parking.controllers;

import com.example.parking.entities.AutoEntity;
import com.example.parking.entities.ClientEntity;
import com.example.parking.entities.ParkingEntity;
import com.example.parking.entities.ParkingLotEntity;
import com.example.parking.repos.AutoRepository;
import com.example.parking.repos.ClientRepository;
import com.example.parking.repos.ParkingLotRepository;
import com.example.parking.repos.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class ParkingController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AutoRepository autoRepository;
    @Autowired
    private ParkingLotRepository lotRepository;
    @Autowired
    private ParkingRepository parkingRepository;

    @GetMapping(path="/parkingAdd")
    public String showAllParking (Map<String, Object> model) {
        Iterable<ClientEntity> clients = clientRepository.findAll();
        Iterable<AutoEntity> autos = autoRepository.findAll();
        Iterable<ParkingLotEntity> lots = lotRepository.findAll();
        Iterable<ParkingEntity> parking = parkingRepository.findAll();

        model.put("clients", clients);
        model.put("autos", autos);
        model.put("lots", lots);
        model.put("parking", parking);
        return "parkingAdd";
    }

    @PostMapping(path="/parkingAdd") // Map ONLY POST Requests
    public String addNewParking (@RequestParam int idClient, @RequestParam int idAuto, @RequestParam int idLot, @RequestParam int lotItem,
                                 @RequestParam String dateParking,
                                 @RequestParam String dateDepart, @RequestParam String paid) {

        LocalDateTime dateStart = LocalDateTime.parse(dateParking);
        LocalDateTime dateEnd = LocalDateTime.parse(dateDepart);
        ClientEntity client = clientRepository.findById(idClient).orElseThrow();
        AutoEntity auto = autoRepository.findById(idAuto).orElseThrow();
        ParkingLotEntity lot = lotRepository.findById(idLot).orElseThrow();

        byte pay;
        if (paid == "on")
            pay = 1;
        else
            pay = 0;

        //Вставка данных в БД
        //TODO Обработчик ошибок
        try {
            ParkingEntity p = new ParkingEntity();
            p.setClientByIdClient(client);
            p.setAutoByIdCar(auto);
            p.setParkingLotByIdLot(lot);
            p.setLotItem(lotItem);
            p.setDateParking(dateStart);
            p.setDateDepart(dateEnd);
            p.setPaid(pay);

            parkingRepository.save(p);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/error/addDbError";
        }

        return "redirect:/parkingAll";
    }

    @GetMapping(path="/parkingEdit")
    public String getToEditLot (Map<String, Object> model) {
        Iterable<ParkingEntity> parking = parkingRepository.findAll();
        model.put("parking", parking);
        return "parkingEdit";
    }

    /*@GetMapping(path="/parkingEdit/{id}")
    public String getToEditLot (@PathVariable(value = "id") int idLot, Map<String, Object> model) {
        List<ParkingEntity> parking = parkingRepository.findByIdLot(idLot);

        model.put("parking", parking);
        return "parkingEdit";
    }*/

    @PostMapping(path="/parkingEdit/{id}")
    public String editLot (@RequestParam int idClient, @RequestParam int idAuto, @RequestParam int idLot, @RequestParam int lotItem,
                           @RequestParam Timestamp dateParking, @RequestParam Timestamp dateDepart, @RequestParam byte paid, Map<String, Object> model) {
        ClientEntity client = clientRepository.findById(idClient).orElseThrow();
        AutoEntity auto = autoRepository.findById(idAuto).orElseThrow();
        ParkingLotEntity lot = lotRepository.findById(idLot).orElseThrow();

        //Вставка данных в БД
        try {
            ParkingEntity p = new ParkingEntity();
            p.setClientByIdClient(client);
            p.setAutoByIdCar(auto);
            p.setParkingLotByIdLot(lot);
            p.setLotItem(lotItem);
            //p.setDateParking(dateParking);
            //p.setDateDepart(dateDepart);
            p.setPaid(paid);

            parkingRepository.save(p);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:/error/addDbError";
        }

        Iterable<ParkingEntity> parking = parkingRepository.findAll();
        model.put("parking", parking);
        return "parkingEdit";
    }

    @GetMapping(path="/parkingDelete")
    public String getAllDeleteLot (Map<String, Object> model) {
        Iterable<ParkingEntity> parking = parkingRepository.findAll();

        model.put("parking", parking);
        return "parkingDelete";
    }

    /*@GetMapping(path="/parkingDelete/{id}")
    public String getToDeleteLot (@PathVariable(value = "id") int idLot, Map<String, Object> model) {
        List<ParkingEntity> parking = parkingRepository.findByIdLot(idLot);

        model.put("parking", parking);
        return "parkingDelete";
    }*/

    @PostMapping(path="/parkingDelete" )
    public String deleteLot (@RequestParam int idLot, Map<String, Object> model) {
        ParkingEntity a = parkingRepository.findById(idLot).orElseThrow();
        parkingRepository.delete(a);

        Iterable<ParkingEntity> parking = parkingRepository.findAll();
        model.put("parking", parking);
        return "parkingDelete";
    }

    @PostMapping(path="/parkingDelete/{id}")
    public String deleteLotWithId (@RequestParam int idLot, Map<String, Object> model) {
        //ParkingEntity a = parkingRepository.findById(idLot).orElseThrow();
        //parkingRepository.delete(a);

        Iterable<ParkingEntity> parking = parkingRepository.findAll();
        model.put("parking", parking);
        return "parkingDelete";
    }

    @GetMapping(path="/parkingAll")
    public String getAllLot (Map<String, Object> model) {
        Iterable<ParkingEntity> parking = parkingRepository.findAll();

        model.put("parking", parking);
        return "parkingAll";
    }

    //FIXME parkingEdit/parkingEdit/filter
    /*@PostMapping("/parkingAll")
    public String filter (@RequestParam String address, Map<String, Object> model)
    {
        List<ParkingEntity> parking = parkingRepository.findByAddress(address);

        model.put("parking", parking);
        return "parkingAll";
    }*/
}