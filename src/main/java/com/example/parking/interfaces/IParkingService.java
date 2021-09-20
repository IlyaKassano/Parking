package com.example.parking.interfaces;

import com.example.parking.enums.ActionFront;
import com.example.parking.exception.InternalException;
import org.springframework.ui.Model;

public interface IParkingService {
    void getForm(ActionFront act, Model model);
    void addNewParking (int idClient, int idAuto, int idLot, int lotItem,
                    String dateParking, String dateDepart, String paid) throws InternalException;
    void findAllParking (ActionFront act, Model model);
    void findParkingById (int idParking, ActionFront act, Model model);
    void editParkingById (int idParking, int idClient, int idAuto, int idLot, int lotItem,
                          String dateParking, String dateDepart, String paid, Model model)
            throws InternalException;
    void deleteParkingById (int idParking, Model model);
    void getParkingByPrimaryKeys (int idClient, int idAuto, int idParking, ActionFront act, Model model);
    boolean checkValidLotItem(int idLot, int lotItem);
}
