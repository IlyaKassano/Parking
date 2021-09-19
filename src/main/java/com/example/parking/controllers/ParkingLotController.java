package com.example.parking.controllers;

import com.example.parking.enums.ActionFront;
import com.example.parking.interfaces.IParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@Controller
public class ParkingLotController {
    private IParkingLotService lotService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IParkingLotService service) {
        this.lotService = service;
    }

    //Получить пустую форму
    @GetMapping(path="/parkingLotAdd")
    public String getEmptyForm(Model model) {
        lotService.getForm(ActionFront.ADD, model);
        return "parkingLot/parkingLot";
    }

    //Обработка запроса на добавление новой записи
    @PostMapping(path="/parkingLotAdd")
    public String addNewLot (@RequestParam String name, @RequestParam String address,
                             @RequestParam int numLots, @RequestParam BigDecimal price,
                             HttpServletResponse response) {
        lotService.addNewLot(name, address, numLots, price, response);
        return "redirect:/parkingLotAll";
    }

    //Получить форму всех записей для редактирования
    @GetMapping(path="/parkingLotEdit")
    public String getFormEditLot(Model model) {
        lotService.findAllLot(ActionFront.EDIT, model);
        return "parkingLot/parkingLot";
    }

    //Получить форму для редактирования по айди
    @GetMapping(path="/parkingLotEdit/{id}")
    public String getFormEditLotById(@PathVariable(value = "id") int idLot, Model model) {
        lotService.findLotById(idLot, ActionFront.EDIT, model);
        return "parkingLot/parkingLot";
    }

    //Обработка изменения данных из формы
    @PostMapping(path="/parkingLotEdit")
    public String putLotById(@RequestParam int idLot, @RequestParam String name,
                             @RequestParam String address, @RequestParam int numLots,
                             @RequestParam BigDecimal price, Model model,
                             HttpServletResponse response) {
        lotService.editLotById(idLot, name, address, numLots, price, model, response);
        return "redirect:/parkingLotEdit";
    }

    //Изменение данных происходит при передачи айди по ссылке
    @PostMapping(path="/parkingLotEdit/{id}")
    public String putLotByPathId(@PathVariable(value = "id") int idLot, @RequestParam String name,
                                 @RequestParam String address, @RequestParam int numLots,
                                 @RequestParam BigDecimal price, Model model,
                                 HttpServletResponse response) {
        lotService.editLotById(idLot, name, address, numLots, price, model, response);
        return "redirect:/parkingLotEdit";
    }

    //Получить форму всех записей на удаление
    @GetMapping(path="/parkingLotDelete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllDeleteLot (Model model) {
        lotService.findAllLot(ActionFront.DELETE, model);
        return "parkingLot/parkingLot";
    }

    //Получить форму с заданным айди на удаление
    @GetMapping(path="/parkingLotDelete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getToDeleteLot (@PathVariable(value = "id") int idLot, Model model) {
        lotService.findLotById(idLot, ActionFront.DELETE, model);
        return "parkingLot/parkingLot";
    }

    //Обработка удаления
    @PostMapping(path="/parkingLotDelete" )
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteLotById(@RequestParam int idLot, Model model) {
        lotService.deleteLotById(idLot, model);
        return "parkingLot/parkingLot";
    }

    //Обработка удаления по айди
    @PostMapping(path="/parkingLotDelete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteLotByPathId(@PathVariable(value = "id") int idLot, Model model) {
        lotService.deleteLotById(idLot, model);
        return "parkingLot/parkingLot";
    }

    //Получение формы всех записей
    @GetMapping(path="/parkingLotAll")
    public String getAllLot (Model model) {
        lotService.findAllLot(ActionFront.ALL, model);
        return "parkingLot/parkingLot";
    }

    //Фильтрация данных на форме
    @PostMapping("/parkingLotAll")
    public String filter (@RequestParam String name, Model model)
    {
        lotService.getLotByName(name, ActionFront.ALL, model);
        return "parkingLot/parkingLot";
    }
}