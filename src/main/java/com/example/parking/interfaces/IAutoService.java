package com.example.parking.interfaces;

import com.example.parking.enums.ActionFront;
import com.example.parking.exception.InternalException;
import org.springframework.ui.Model;

public interface IAutoService {
    void getForm(ActionFront act, Model model);
    void addNewAuto (String brand, String autoModel) throws InternalException;
    void findAllAuto (ActionFront act, Model model);
    void findAutoById (int idAuto, ActionFront act, Model model);
    void editAutoById (int idAuto, String brand, String autoModel,  Model model) throws InternalException;
    void deleteAutoById (int idAuto, Model model);
    void getAutoByBrandAndModel (String brand, String autoModel, ActionFront act, Model model);
}
