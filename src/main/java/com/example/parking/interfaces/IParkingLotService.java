package com.example.parking.interfaces;

import com.example.parking.enums.ActionFront;
import com.example.parking.exception.InternalException;
import org.springframework.ui.Model;

import java.math.BigDecimal;

public interface IParkingLotService {
    void getForm(ActionFront act, Model model);
    void addNewLot (String name, String address, int numLots, BigDecimal price) throws InternalException;
    void findAllLot (ActionFront act, Model model);
    void findLotById (int idLot, ActionFront act, Model model);
    void editLotById (int idLot, String name, String address, int numLots, BigDecimal price, Model model)
            throws InternalException;
    void deleteLotById (int idLot, Model model);
    void getLotByName (String name, ActionFront act, Model model);
}
