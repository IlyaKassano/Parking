package com.example.parking.services;

import com.example.parking.entities.ParkingLotEntity;
import com.example.parking.enums.ActionFront;
import com.example.parking.exception.InternalException;
import com.example.parking.fronttemplates.ActionTemplate;
import com.example.parking.interfaces.IParkingLotService;
import com.example.parking.repos.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    public void addNewLot (String name, String address, int numLots, BigDecimal price) throws InternalException {
        ParkingLotEntity lot = new ParkingLotEntity();
        insertToRepo(lot, name, address, numLots, price);
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

    public void editLotById (int idLot, String name, String address, int numLots, BigDecimal price, Model model)
            throws InternalException
    {
        ParkingLotEntity lot = lotRepository.findById(idLot).orElseThrow();
        insertToRepo(lot, name, address, numLots, price);

        findAllLot(ActionFront.EDIT, model);
    }

    public void deleteLotById (int idLot, Model model) {
        ParkingLotEntity a = lotRepository.findById(idLot).orElseThrow();
        try {
            lotRepository.delete(a);
        }
        catch(Exception e) {
            model.addAttribute("err", "Произошла ошибка при удалении! Скорее всего удалению препятсвутют связи.");
        }

        findAllLot(ActionFront.DELETE, model);
    }

    public void getLotByName (String name, ActionFront act, Model model)
    {
        List<ParkingLotEntity> lots = lotRepository.findByName(name);

        getForm(act, model);
        model.addAttribute("lots", lots);
    }

    /**
     * Добавление или редактирование данных в репозитории
     * @param lot Сущность парковочного места
     * @param name ФИО клиента
     * @param address Телефон клиента
     * @param price Цена за паркоку
     */
    private void insertToRepo(ParkingLotEntity lot, String name, String address, int numLots, BigDecimal price)
            throws InternalException
    {
        try {
            lot.setName(name);
            lot.setAddress(address);
            lot.setNumLots(numLots);
            lot.setPrice(price);

            lotRepository.save(lot);
        }
        catch(Exception e) {
            throw new InternalException(500, "Ошибка при добавлении/редактировании данных.");
        }
    }
}