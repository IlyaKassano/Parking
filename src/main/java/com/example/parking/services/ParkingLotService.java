package com.example.parking.services;

import com.example.parking.entities.ParkingLotEntity;
import com.example.parking.interfaces.IParkingLotService;
import com.example.parking.repos.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
public class ParkingLotService implements IParkingLotService {
    @Autowired
    private ParkingLotRepository lotsRepository;

    public String showAllLots () {
        return "parkingLotAdd";
    }

    public String addNewLot (String name, String address, int numLots, BigDecimal price, final HttpServletResponse response) {
        ParkingLotEntity lot = new ParkingLotEntity();
        insertToDb(lot, name, address, numLots, price, response);

        return "redirect:/parkingLotAll";
    }

    public String getToEditLot (Map<String, Object> model) {
        Iterable<ParkingLotEntity> lots = lotsRepository.findAll();
        model.put("lots", lots);
        return "parkingLotEdit";
    }

    public String getOneToEditLot (int idLot, Map<String, Object> model) {
        List<ParkingLotEntity> lots = lotsRepository.findByIdLot(idLot);

        model.put("lots", lots);
        return "parkingLotEdit";
    }

    public String editLot (int idLot, Map<String, Object> model) {

        Iterable<ParkingLotEntity> lots = lotsRepository.findAll();
        model.put("lots", lots);
        return "redirect:/parkingLotEdit/" + idLot;
    }

    public String editByIdLot (int idLot, String name, String address, int numLots,
                           BigDecimal price, Map<String, Object> model, HttpServletResponse response) {
        ParkingLotEntity lot = lotsRepository.findById(idLot).orElseThrow();
        insertToDb(lot, name, address, numLots, price, response);

        Iterable<ParkingLotEntity> lots = lotsRepository.findAll();
        model.put("lots", lots);
        return "redirect:/parkingLotEdit";
    }

    public String getAllDeleteLot (Map<String, Object> model) {
        Iterable<ParkingLotEntity> lots = lotsRepository.findAll();

        model.put("lots", lots);
        return "parkingLotDelete";
    }

    public String getToDeleteLot (int idLot, Map<String, Object> model) {
        List<ParkingLotEntity> lots = lotsRepository.findByIdLot(idLot);

        model.put("lots", lots);
        return "parkingLotDelete";
    }

    public String deleteLot (int idLot, Map<String, Object> model) {
        ParkingLotEntity a = lotsRepository.findById(idLot).orElseThrow();
        lotsRepository.delete(a);

        Iterable<ParkingLotEntity> lots = lotsRepository.findAll();
        model.put("lots", lots);
        return "parkingLotDelete";
    }

    public String deleteLotWithId (int idLot, Map<String, Object> model) {
        ParkingLotEntity a = lotsRepository.findById(idLot).orElseThrow();
        lotsRepository.delete(a);

        Iterable<ParkingLotEntity> lots = lotsRepository.findAll();
        model.put("lots", lots);
        return "parkingLotDelete";
    }

    public String getAllLot (Map<String, Object> model) {
        Iterable<ParkingLotEntity> lots = lotsRepository.findAll();

        model.put("lots", lots);
        return "parkingLotAll";
    }

    public String filter (String name, Map<String, Object> model)
    {
        List<ParkingLotEntity> lots = lotsRepository.findByName(name);

        model.put("lots", lots);
        return "parkingLotAll";
    }

    /**
     * Добавление или редактирование данных в базе
     * @param lot Сущность парковочного места
     * @param name Наименвоание парковки
     * @param address Адресс парковки
     * @param numLots Номер парковочного места
     * @param price Цена за парковку
     * @param response Ответ для передачи ошибки
     */
    public void insertToDb(ParkingLotEntity lot, String name, String address, int numLots,
                           BigDecimal price, HttpServletResponse response){
        try {
            lot.setName(name);
            lot.setAddress(address);
            lot.setNumLots(numLots);
            lot.setPrice(price);

            lotsRepository.save(lot);
        }
        catch(Exception e) {
            try {
                response.sendError(461);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}