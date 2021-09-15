package com.example.parking.interfaces;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IParkingService {
    String showAllParking (Map<String, Object> model);
    String addNewParking (int idClient, int idAuto, int idLot, int lotItem, String dateParking,
                          String dateDepart, String paid, HttpServletResponse response);
    String getToEditParking (Map<String, Object> model);
    String getOneToEditParking (int idParking, Map<String, Object> model);
    String editParking (int idParking, Map<String, Object> model);
    String editByIdParking (int idParking, int idClient, int idAuto, int idLot, int lotItem,
                            String dateParking, String dateDepart, String paid,
                            Map<String, Object> model, HttpServletResponse response);
    String getAllDeleteParking (Map<String, Object> model);
    String getToDeleteParking (int idParking, Map<String, Object> model);
    String deleteParking (int idParking, Map<String, Object> model);
    String deleteParkingWithId (int idParking, Map<String, Object> model);
    String getAllParking (Map<String, Object> model);
    String filter (int idClient, int idAuto, int idLot, Map<String, Object> model);
}
