package com.example.parking.interfaces;

import com.example.parking.enums.ActionFront;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

public interface IParkingService {
    void getForm(ActionFront act, Model model);
    void addNewParking (int idClient, int idAuto, int idLot, int lotItem,
                    String dateParking, String dateDepart, String paid,
                    final HttpServletResponse response);
    void findAllParking (ActionFront act, Model model);
    void findParkingById (int idParking, ActionFront act, Model model);
    void editParkingById (int idParking, int idClient, int idAuto, int idLot, int lotItem,
                          String dateParking, String dateDepart, String paid,
                          Model model, HttpServletResponse response);
    void deleteParkingById (int idParking, Model model);
    void getParkingByPrimaryKeys (int idClient, int idAuto, int idParking, ActionFront act, Model model);
    boolean checkValidLotItem(int idLot, int lotItem);
}
