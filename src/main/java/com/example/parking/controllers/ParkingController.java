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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
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
        putEntitiesToModel(model);
        return "parkingAdd";
    }

    @PostMapping(path="/parkingAdd")
    public String addNewParking (@RequestParam int idClient, @RequestParam int idAuto,
                                 @RequestParam int idLot, @RequestParam int lotItem,
                                 @RequestParam String dateParking, @RequestParam String dateDepart,
                                 @RequestParam(value = "paid", required = false) String paid,
                                 final HttpServletResponse response) {

        //Связанные сущности для вставки в таблицу
        ClientEntity client = clientRepository.findById(idClient).orElseThrow();
        AutoEntity auto = autoRepository.findById(idAuto).orElseThrow();
        ParkingLotEntity lot = lotRepository.findById(idLot).orElseThrow();

        if (checkValidLotItem(lot, lotItem)) {
            try {
                response.sendError(460);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //Вставка данных в БД
        //TODO Обработчик ошибок
        try {
            ParkingEntity p = new ParkingEntity();
            insertToDb(p, client, auto, lot, lotItem, dateParking, dateDepart, paid);
        }
        catch(Exception e) {
            e.printStackTrace();
            return "redirect:/error/addDbError";
        }

        return "redirect:/parkingAll";
    }

    @GetMapping(path="/parkingEdit")
    public String getToEditParking (Map<String, Object> model) {
        putEntitiesToModel(model);
        return "parkingEdit";
    }

    @GetMapping(path="/parkingEdit/{id}")
    public String getToEditLot (@PathVariable(value = "id") int idParking, Map<String, Object> model) {
        List<ParkingEntity> parking = parkingRepository.findByIdParking(idParking);
        Iterable<ClientEntity> clients = clientRepository.findAll();
        Iterable<AutoEntity> autos = autoRepository.findAll();
        Iterable<ParkingLotEntity> lots = lotRepository.findAll();

        model.put("clients", clients);
        model.put("autos", autos);
        model.put("lots", lots);
        model.put("parking", parking);
        return "parkingEdit";
    }

    @PostMapping(path="/parkingEdit")
    public String editParking (@RequestParam int idParking, Map<String, Object> model) {
        putEntitiesToModel(model);
        return "redirect:/parkingEdit/" + idParking;
    }

    @PostMapping(path="/parkingEdit/{id}")
    public String editByIdParking (@PathVariable(value = "id") int idParking, @RequestParam int idClient,
                               @RequestParam int idAuto, @RequestParam int idLot, @RequestParam int lotItem,
                               @RequestParam String dateParking, @RequestParam String dateDepart,
                               @RequestParam(value = "paid", required = false) String paid, Map<String, Object> model) {

        ParkingEntity p = parkingRepository.findById(idParking).orElseThrow();
        ClientEntity client = clientRepository.findById(idClient).orElseThrow();
        AutoEntity auto = autoRepository.findById(idAuto).orElseThrow();
        ParkingLotEntity lot = lotRepository.findById(idLot).orElseThrow();

        //Вставка данных в БД
        try {
            insertToDb(p, client, auto, lot, lotItem, dateParking, dateDepart, paid);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:/error/addDbError";
        }

        putEntitiesToModel(model);
        return "redirect:/parkingEdit";
    }

    @GetMapping(path="/parkingDelete")
    public String getAllDeleteParking (Map<String, Object> model) {
        Iterable<ParkingEntity> parking = parkingRepository.findAll();

        model.put("parking", parking);
        return "parkingDelete";
    }

   @GetMapping(path="/parkingDelete/{id}")
    public String getToDeleteParking (@PathVariable(value = "id") int idParking, Map<String, Object> model) {
        ParkingEntity parking = parkingRepository.findById(idParking).orElseThrow();

        model.put("parking", parking);
        return "parkingDelete";
    }

    @PostMapping(path="/parkingDelete" )
    public String deleteParking (@RequestParam int idParking, Map<String, Object> model) {
        ParkingEntity a = parkingRepository.findById(idParking).orElseThrow();
        parkingRepository.delete(a);

        putToModel(model);
        return "parkingDelete";
    }

    @PostMapping(path="/parkingDelete/{id}")
    public String deleteParkingWithId (@RequestParam int idParking, Map<String, Object> model) {
        ParkingEntity a = parkingRepository.findById(idParking).orElseThrow();
        parkingRepository.delete(a);

        putToModel(model);
        return "parkingDelete";
    }

    @GetMapping(path="/parkingAll")
    public String getAllParking (Map<String, Object> model) {
        putEntitiesToModel(model);
        return "parkingAll";
    }

    @PostMapping("/parkingAll")
    public String filter (@RequestParam int idClient, @RequestParam int idAuto,
                          @RequestParam int idLot, Map<String, Object> model)
    {
        //Связанные сущности
        ClientEntity clients = clientRepository.findById(idClient).orElseThrow();
        AutoEntity autos = autoRepository.findById(idAuto).orElseThrow();
        ParkingLotEntity lots = lotRepository.findById(idLot).orElseThrow();
        Iterable<ParkingEntity> parking = parkingRepository.findByClientByIdClientAndAutoByIdCarAndParkingLotByIdLot(clients, autos, lots);

        model.put("clients", clients);
        model.put("autos", autos);
        model.put("lots", lots);
        model.put("parking", parking);
        return "parkingAll";
    }

    private void putToModel(Map<String, Object> model) {
        Iterable<ParkingEntity> parking = parkingRepository.findAll();
        model.put("parking", parking);
    }

    private void putEntitiesToModel(Map<String, Object> model){
        Iterable<ClientEntity> clients = clientRepository.findAll();
        Iterable<AutoEntity> autos = autoRepository.findAll();
        Iterable<ParkingLotEntity> lots = lotRepository.findAll();
        Iterable<ParkingEntity> parking = parkingRepository.findAll();

        model.put("clients", clients);
        model.put("autos", autos);
        model.put("lots", lots);
        model.put("parking", parking);
    }

    private void insertToDb(ParkingEntity p, ClientEntity client, AutoEntity auto, ParkingLotEntity lot,
                            int lotItem, String dateParking, String dateDepart, String paid) {

        //Дата возвращается в формате строки, поэтому приходится дополнительно парсить строку с датой
        LocalDateTime dateStart = LocalDateTime.parse(dateParking);
        //Может вернуться пустой
        LocalDateTime dateEnd;

        //CheckBox возращает строку, а база требует byte
        byte pay;
        if (paid == null)
            pay = 0;
        else
            pay = 1;

        p.setClientByIdClient(client);
        p.setAutoByIdCar(auto);
        p.setParkingLotByIdLot(lot);
        p.setLotItem(lotItem);
        p.setDateParking(dateStart);
        if(dateDepart == null || dateDepart == "") {
            p.setDateDepart(null);
        }
        else {
            dateEnd = LocalDateTime.parse(dateDepart);
            p.setDateDepart(dateEnd);
        }

        p.setPaid(pay);

        parkingRepository.save(p);
    }

    private boolean checkValidLotItem(ParkingLotEntity lot, int lotItem) {
        List<ParkingEntity> p = parkingRepository.findByParkingLotByIdLotAndLotItemAndDateDepartIsNull(lot, lotItem);
        return p.size() != 0;
    }
}