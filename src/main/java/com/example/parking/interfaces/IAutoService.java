package com.example.parking.interfaces;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IAutoService {
    String showAllAutos ();
    String addNewAuto (String brand, String autoModel, HttpServletResponse response);
    String getToEditAuto (Map<String, Object> model);
    String getOneToEditAuto (int idAuto, Map<String, Object> model);
    String editAuto (int idAuto, Map<String, Object> model);
    String editByIdAuto (int idAuto, String brand, String autoModel, Map<String, Object> model, HttpServletResponse response);
    String getAllDeleteAuto (Map<String, Object> model);
    String getToDeleteAuto (int idAuto, Map<String, Object> model);
    String deleteAuto (int idAuto, Map<String, Object> model);
    String deleteAutoWithId (int idAuto, Map<String, Object> model);
    String getAllAuto (Map<String, Object> model);
    String filter (String brand, String moddel, Map<String, Object> model);
}
