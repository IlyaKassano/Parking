package com.example.parking.interfaces;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public interface IParkingLotService {
    void addNewLot (String name, String address, int numLots, BigDecimal price,
                    HttpServletResponse response);
    void findAllLot (Model model);
    void findLotById (int idLot, Model model);
    void editLotById (int idLot, String name, String address, int numLots, BigDecimal price,
                          Model model, final HttpServletResponse response);
    void deleteLotById (int idLot, Model model);
    void getLotByName (String name, Model model);
}
