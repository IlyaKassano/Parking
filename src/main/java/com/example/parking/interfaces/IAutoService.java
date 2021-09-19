package com.example.parking.interfaces;

import com.example.parking.enums.ActionFront;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

public interface IAutoService {
    void getForm(ActionFront act, Model model);
    void addNewAuto (String brand, String autoModel, final HttpServletResponse response);
    void findAllAuto (ActionFront act, Model model);
    void findAutoById (int idAuto, ActionFront act, Model model);
    void editAutoById ( int idAuto, String brand, String autoModel,
                               Model model, final HttpServletResponse response);
    void deleteAutoById (int idAuto, Model model);
    void getAutoByBrandAndModel (String brand, String autoModel, ActionFront act, Model model);
}
