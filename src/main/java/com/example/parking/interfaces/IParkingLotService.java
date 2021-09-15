package com.example.parking.interfaces;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Map;

public interface IParkingLotService {
    String showAllLots ();
    String addNewLot (String name, String address, int numLots, BigDecimal price, HttpServletResponse response);
    String getToEditLot (Map<String, Object> model);
    String getOneToEditLot (int idLot, Map<String, Object> model);
    String editLot (int idLot, Map<String, Object> model);
    String editByIdLot (int idLot, String name, String address, int numLots,
                        BigDecimal price, Map<String, Object> model, HttpServletResponse response);
    String getAllDeleteLot (Map<String, Object> model);
    String getToDeleteLot (int idLot, Map<String, Object> model);
    String deleteLot (int idLot, Map<String, Object> model);
    String deleteLotWithId (int idLot, Map<String, Object> model);
    String getAllLot (Map<String, Object> model);
    String filter (String name, Map<String, Object> model);
}
