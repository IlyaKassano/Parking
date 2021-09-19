package com.example.parking.services;

import com.example.parking.entities.ParkingLotEntity;
import com.example.parking.enums.ActionFront;
import com.example.parking.fronttemplates.ActionTemplate;
import com.example.parking.interfaces.IParkingLotService;
import com.example.parking.repos.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ParkingLotService implements IParkingLotService {
    @Autowired
    private ParkingLotRepository lotRepository;

    public void getForm(ActionFront act, Model model) {
        ActionTemplate action = new ActionTemplate(act);
        if(act == ActionFront.DELETE)
            action.setReadonly(true);

        model.addAttribute("action", action);
    }

    public void addNewLot (String name, String address, int numLots, BigDecimal price, final HttpServletResponse response) {
        ParkingLotEntity lot = new ParkingLotEntity();
        insertToDb(lot, name, address, numLots, price, response);
    }

    public void findAllLot (ActionFront act, Model model) {
        Iterable<ParkingLotEntity> lots = lotRepository.findAll();

        getForm(act, model);
        model.addAttribute("lots", lots);
    }

    public void findLotById (int idLot, ActionFront act, Model model) {
        List<ParkingLotEntity> lots = lotRepository.findByIdLot(idLot);

        getForm(act, model);
        model.addAttribute("lots", lots);
    }

    public void editLotById (int idLot, String name, String address, int numLots,
                             BigDecimal price,
                                 Model model, HttpServletResponse response) {
        ParkingLotEntity lot = lotRepository.findById(idLot).orElseThrow();
        insertToDb(lot, name, address, numLots, price, response);

        findAllLot(ActionFront.EDIT, model);
    }

    public void deleteLotById (int idLot, Model model) {
        ParkingLotEntity a = lotRepository.findById(idLot).orElseThrow();
        lotRepository.delete(a);

        findAllLot(ActionFront.DELETE, model);
    }

    public void getLotByName (String name, ActionFront act, Model model)
    {
        List<ParkingLotEntity> lots = lotRepository.findByName(name);

        getForm(act, model);
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