package com.example.parking.services;

import com.example.parking.entities.AutoEntity;
import com.example.parking.interfaces.IAutoService;
import com.example.parking.repos.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class AutoService implements IAutoService {
    @Autowired
    private AutoRepository autoRepository;

    public String showAllAutos () {
        return "autoAdd";
    }

    public String addNewAuto (String brand, String autoModel, final HttpServletResponse response) {
        AutoEntity auto = new AutoEntity();
        insertToDb(auto, brand, autoModel, response);

        return "redirect:/autoAll";
    }

    public String getToEditAuto (Map<String, Object> model) {
        Iterable<AutoEntity> autos = autoRepository.findAll();
        model.put("autos", autos);
        return "autoEdit";
    }

    public String getOneToEditAuto (int idAuto, Map<String, Object> model) {
        List<AutoEntity> autos = autoRepository.findByIdAuto(idAuto);

        model.put("autos", autos);
        return "autoEdit";
    }

    public String editAuto (@RequestParam int idAuto, Map<String, Object> model) {
        Iterable<AutoEntity> autos = autoRepository.findAll();
        model.put("autos", autos);
        return "redirect:/autoEdit/" + idAuto;
    }

    public String editByIdAuto ( int idAuto, String brand, String autoModel,
                            Map<String, Object> model, final HttpServletResponse response) {
        AutoEntity auto = autoRepository.findById(idAuto).orElseThrow();
        insertToDb(auto, brand, autoModel, response);

        Iterable<AutoEntity> autos = autoRepository.findAll();
        model.put("autos", autos);
        return "redirect:/autoEdit";
    }

    public String getAllDeleteAuto (Map<String, Object> model) {
        Iterable<AutoEntity> autos = autoRepository.findAll();

        model.put("autos", autos);
        return "autoDelete";
    }

    public String getToDeleteAuto (int idAuto, Map<String, Object> model) {
        List<AutoEntity> autos = autoRepository.findByIdAuto(idAuto);

        model.put("autos", autos);
        return "autoDelete";
    }

    public String deleteAuto (int idAuto, Map<String, Object> model) {
        AutoEntity a = autoRepository.findById(idAuto).orElseThrow();
        autoRepository.delete(a);

        Iterable<AutoEntity> autos = autoRepository.findAll();
        model.put("autos", autos);
        return "autoDelete";
    }

    public String deleteAutoWithId (int idAuto, Map<String, Object> model) {
        AutoEntity a = autoRepository.findById(idAuto).orElseThrow();
        autoRepository.delete(a);

        Iterable<AutoEntity> autos = autoRepository.findAll();
        model.put("autos", autos);
        return "autoDelete";
    }

    public String getAllAuto (Map<String, Object> model) {
        Iterable<AutoEntity> autos = autoRepository.findAll();

        model.put("autos", autos);
        return "autoAll";
    }

    public String filter (String brand, String autoModel, Map<String, Object> model)
    {
        List<AutoEntity> autos = autoRepository.findByBrandAndModel(brand, autoModel);

        model.put("autos", autos);
        return "autoAll";
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