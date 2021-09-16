package com.example.parking.services;

import com.example.parking.entities.ParkingLotEntity;
import com.example.parking.interfaces.IParkingLotService;
import com.example.parking.repos.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class ParkingLotService implements IParkingLotService {
    @Autowired
    private ParkingLotRepository lotRepository;

    public void addNewLot (String name, String address, int numLots, BigDecimal price, final HttpServletResponse response) {
        ParkingLotEntity lot = new ParkingLotEntity();
        insertToDb(lot, name, address, numLots, price, response);
    }

    public void findAllLot (Model model) {
        Iterable<ParkingLotEntity> lots = lotRepository.findAll();
        model.addAttribute("lots", lots);
    }

    public void findLotById (int idLot, Model model) {
        List<ParkingLotEntity> lots = lotRepository.findByIdLot(idLot);
        model.addAttribute("lots", lots);
    }

    public void editLotById (int idLot, String name, String address, int numLots,
                             BigDecimal price,
                                 Model model, HttpServletResponse response) {
        ParkingLotEntity lot = lotRepository.findById(idLot).orElseThrow();
        insertToDb(lot, name, address, numLots, price, response);

        findAllLot(model);
    }

    public void deleteLotById (int idLot, Model model) {
        ParkingLotEntity a = lotRepository.findById(idLot).orElseThrow();
        lotRepository.delete(a);

        findAllLot(model);
    }

    public void getLotByName (String name, Model model)
    {
        List<ParkingLotEntity> lots = lotRepository.findByName(name);

        model.addAttribute("lots", lots);
    }

    /**
     * Добавление или редактирование данных в базе
     * @param lot Сущность парковочного места
     * @param name ФИО клиента
     * @param address Телефон клиента
     * @param price Цена за паркоку
     */
    private void insertToDb(ParkingLotEntity lot, String name, String address, int numLots,
                           BigDecimal price, HttpServletResponse response){
        try {
            lot.setName(name);
            lot.setAddress(address);
            lot.setNumLots(numLots);
            lot.setPrice(price);

            lotRepository.save(lot);
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