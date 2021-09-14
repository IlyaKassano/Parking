package com.example.parking.controllers;

import com.example.parking.entities.AutoEntity;
import com.example.parking.repos.AutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class AutoController {
    @Autowired
    private AutoRepository autoRepository;

    @GetMapping(path="/autoAdd")
    public String showAllAutos () {
        return "autoAdd";
    }

    /*@GetMapping(path="/autoAdd/{id}")
    public String findAuto (@PathVariable(value = "id") int idAuto, Map<String, Object> model) {
        List<AutoEntity> auto = autoRepository.findByIdAuto(idAuto);
        Iterable<AutoEntity> autos = autoRepository.findAll();

        model.put("auto", auto);
        model.put("autos", autos);
        return "autoAdd";
    }*/

    @PostMapping(path="/autoAdd") // Map ONLY POST Requests
    public String addNewAuto (@RequestParam String brand, @RequestParam String autoModel) {
        //Вставка данных в БД
        try {
            AutoEntity auto = new AutoEntity();
            auto.setBrand(brand);
            auto.setModel(autoModel);

            autoRepository.save(auto);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            //TODO Обработчик ошибок
            return "redirect:/error/addDbError";
        }

        return "redirect:/autoAll";
    }

    @GetMapping(path="/autoEdit")
    public String getToEditAuto (Map<String, Object> model) {
        Iterable<AutoEntity> autos = autoRepository.findAll();
        model.put("autos", autos);
        return "autoEdit";
    }

    @GetMapping(path="/autoEdit/{id}")
    public String getToEditAuto (@PathVariable(value = "id") int idAuto, Map<String, Object> model) {
        List<AutoEntity> autos = autoRepository.findByIdAuto(idAuto);

        model.put("autos", autos);
        return "autoEdit";
    }

    @PostMapping(path="/autoEdit")
    public String editAuto (@RequestParam int idAuto, Map<String, Object> model) {
        Iterable<AutoEntity> autos = autoRepository.findAll();
        model.put("autos", autos);
        return "redirect:/autoEdit/" + idAuto;
    }

    @PostMapping(path="/autoEdit/{id}")
    public String editByIdAuto (@PathVariable(value = "id") int idAuto, @RequestParam String brand, @RequestParam String autoModel,
                            Map<String, Object> model) {
        AutoEntity auto = autoRepository.findById(idAuto).orElseThrow();

        //Вставка данных в БД
        try {
            auto.setBrand(brand);
            auto.setModel(autoModel);

            autoRepository.save(auto);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:/error/addDbError";
        }

        Iterable<AutoEntity> autos = autoRepository.findAll();
        model.put("autos", autos);
        return "redirect:/autoEdit";
    }

    @GetMapping(path="/autoDelete")
    public String getAllDeleteAuto (Map<String, Object> model) {
        Iterable<AutoEntity> autos = autoRepository.findAll();

        model.put("autos", autos);
        return "autoDelete";
    }

    @GetMapping(path="/autoDelete/{id}")
    public String getToDeleteAuto (@PathVariable(value = "id") int idAuto, Map<String, Object> model) {
        List<AutoEntity> autos = autoRepository.findByIdAuto(idAuto);

        model.put("autos", autos);
        return "autoDelete";
    }

    @PostMapping(path="/autoDelete" )
    public String deleteAuto (@RequestParam int idAuto, Map<String, Object> model) {
        AutoEntity a = autoRepository.findById(idAuto).orElseThrow();
        autoRepository.delete(a);

        Iterable<AutoEntity> autos = autoRepository.findAll();
        model.put("autos", autos);
        return "autoDelete";
    }

    @PostMapping(path="/autoDelete/{id}")
    public String deleteAutoWithId (@RequestParam int idAuto, Map<String, Object> model) {
        AutoEntity a = autoRepository.findById(idAuto).orElseThrow();
        autoRepository.delete(a);

        Iterable<AutoEntity> autos = autoRepository.findAll();
        model.put("autos", autos);
        return "autoDelete";
    }

    @GetMapping(path="/autoAll")
    public String getAllAuto (Map<String, Object> model) {
        Iterable<AutoEntity> autos = autoRepository.findAll();

        model.put("autos", autos);
        return "autoAll";
    }

    @PostMapping("/autoAll")
    public String filter (@RequestParam String brand, @RequestParam String autoModel, Map<String, Object> model)
    {
        List<AutoEntity> autos = autoRepository.findByBrandAndModel(brand, autoModel);

        model.put("autos", autos);
        return "autoAll";
    }
}