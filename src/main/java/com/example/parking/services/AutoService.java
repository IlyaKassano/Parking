package com.example.parking.services;

import com.example.parking.entities.AutoEntity;
import com.example.parking.interfaces.IAutoService;
import com.example.parking.repos.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class AutoService implements IAutoService {
    @Autowired
    private AutoRepository autoRepository;

    public void addNewAuto (String brand, String autoModel, final HttpServletResponse response) {
        AutoEntity auto = new AutoEntity();
        insertToDb(auto, brand, autoModel, response);
    }

    public void findAllAuto (Model model) {
        Iterable<AutoEntity> autos = autoRepository.findAll();
        model.addAttribute("autos", autos);
    }

    public void findAutoById (int idAuto, Model model) {
        List<AutoEntity> autos = autoRepository.findByIdAuto(idAuto);
        model.addAttribute("autos", autos);
    }

    public void editAutoById ( int idAuto, String brand, String autoModel,
                            Model model, final HttpServletResponse response) {
        AutoEntity auto = autoRepository.findById(idAuto).orElseThrow();
        insertToDb(auto, brand, autoModel, response);

        findAllAuto(model);
    }

    public void deleteAutoById (int idAuto, Model model) {
        AutoEntity a = autoRepository.findById(idAuto).orElseThrow();
        autoRepository.delete(a);

        findAllAuto(model);
    }

    public void getAutoByBrandAndModel (String brand, String autoModel, Model model)
    {
        List<AutoEntity> autos = autoRepository.findByBrandAndModel(brand, autoModel);
        model.addAttribute("autos", autos);
    }

    /**
     * Добавление или редактирование данных в базе
     * @param auto Сущность автомобиля
     * @param brand Марка автомобиля
     * @param autoModel Модель автообиля
     * @param response Ответ для передачи ошибки
     */
    public void insertToDb(AutoEntity auto, String brand, String autoModel, HttpServletResponse response){
        try {
            auto.setBrand(brand);
            auto.setModel(autoModel);

            autoRepository.save(auto);
        }
        catch(Exception e) {
            try {
                response.sendError(461);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}