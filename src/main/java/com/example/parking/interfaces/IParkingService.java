package com.example.parking.interfaces;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

public interface IParkingService {
    void addNewParking (int idClient, int idAuto, int idLot, int lotItem,
                    String dateParking, String dateDepart, String paid,
                    final HttpServletResponse response);
    void findAllParking (Model model);
    void findParkingById (int idParking, Model model);
    void editParkingById (int idParking, int idClient, int idAuto, int idLot, int lotItem,
                          String dateParking, String dateDepart, String paid,
                          Model model, HttpServletResponse response);
    void deleteParkingById (int idParking, Model model);
    void getParkingByPrimaryCodes (int idClient, int idAuto, int idParking, Model model);
    boolean checkValidLotItem(int idLot, int lotItem);
}
