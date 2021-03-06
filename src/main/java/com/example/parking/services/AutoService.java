package com.example.parking.services;

import com.example.parking.entities.AutoEntity;
import com.example.parking.enums.ActionFront;
import com.example.parking.exception.InternalException;
import com.example.parking.fronttemplates.ActionTemplate;
import com.example.parking.interfaces.IAutoService;
import com.example.parking.repos.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AutoService implements IAutoService {
    @Autowired
    private AutoRepository autoRepository;

    public void getForm(ActionFront act, Model model) {
        ActionTemplate action = new ActionTemplate(act);
        if(act == ActionFront.DELETE)
            action.setReadonly(true);

        model.addAttribute("action", action);
    }

    public void addNewAuto (String brand, String autoModel) throws InternalException
    {
        AutoEntity auto = new AutoEntity();
        insertToRepo(auto, brand, autoModel);
    }

    public void findAllAuto (ActionFront act, Model model) {
        Iterable<AutoEntity> autos = autoRepository.findAll();

        getForm(act, model);
        model.addAttribute("autos", autos);
    }

    public void findAutoById (int idAuto, ActionFront act, Model model) {
        List<AutoEntity> autos = autoRepository.findByIdAuto(idAuto);

        getForm(act, model);
        model.addAttribute("autos", autos);
    }

    public void editAutoById ( int idAuto, String brand, String autoModel, Model model) throws InternalException {
        AutoEntity auto = autoRepository.findById(idAuto).orElseThrow();
        insertToRepo(auto, brand, autoModel);

        findAllAuto(ActionFront.EDIT, model);
    }

    public void deleteAutoById (int idAuto, Model model) {
        AutoEntity a = autoRepository.findById(idAuto).orElseThrow();
        try {
            autoRepository.delete(a);
        }
        catch(Exception e) {
            model.addAttribute("err", "Произошла ошибка при удалении! Скорее всего удалению препятсвутют связи.");
        }

        findAllAuto(ActionFront.DELETE, model);
    }

    public void getAutoByBrandAndModel (String brand, String autoModel, ActionFront act, Model model)
    {
        List<AutoEntity> autos = autoRepository.findByBrandAndModel(brand, autoModel);

        getForm(act, model);
        model.addAttribute("autos", autos);
    }

    /**
     * Добавление или редактирование данных в репозитории
     * @param auto Сущность автомобиля
     * @param brand Марка автомобиля
     * @param autoModel Модель автообиля
     */
    public void insertToRepo(AutoEntity auto, String brand, String autoModel) throws InternalException {
        try {
            auto.setBrand(brand);
            auto.setModel(autoModel);

            autoRepository.save(auto);
        }
        catch(Exception e) {
            throw new InternalException(500, "Ошибка при добавлении/редактировании данных.");
        }
    }
}