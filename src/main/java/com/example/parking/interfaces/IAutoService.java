package com.example.parking.interfaces;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

public interface IAutoService {
    void addNewAuto (String brand, String autoModel, final HttpServletResponse response);
    void findAllAuto (Model model);
    void findAutoById (int idAuto, Model model);
    void editAutoById ( int idAuto, String brand, String autoModel,
                               Model model, final HttpServletResponse response);
    void deleteAutoById (int idAuto, Model model);
    void getAutoByBrandAndModel (String brand, String autoModel, Model model);
}
