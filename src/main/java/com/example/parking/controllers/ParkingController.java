package com.example.parking.controllers;

import com.example.parking.enums.ActionFront;
import com.example.parking.exception.InternalException;
import com.example.parking.interfaces.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParkingController {
    private IParkingService parkingService;

    //Разворачивание сервиса
    @Autowired
    public void setService(IParkingService service) {
        this.parkingService = service;
    }

    //Получить пустую форму
    @GetMapping(path="/parkingAdd")
    public String getEmptyForm(Model model) {
        parkingService.findAllParking(ActionFront.ADD, model);
        return "parking/parking";
    }

    //Обработка запроса на добавление новой записи
    @PostMapping(path="/parkingAdd")
    public String addNewParking (@RequestParam int idClient, @RequestParam int idAuto,
                             @RequestParam int idLot, @RequestParam int lotItem,
                             @RequestParam String dateParking, @RequestParam String dateDepart,
                             @RequestParam(value = "paid", required = false) String paid, Model model)
            throws InternalException
    {

        if (parkingService.checkValidLotItem(idLot, lotItem)) {
            parkingService.addNewParking(idClient, idAuto, idLot, lotItem, dateParking, dateDepart, paid);
            return "redirect:/parkingAll";
        }
        else {
            model.addAttribute("err", "Данное парковочное место уже занято, " +
                    "либо его номер превышает допустимый у данной парковки! Попробуйте другое.");
            parkingService.findAllParking(ActionFront.ADD, model);
            return "parking/parking";
        }
    }

    //Получить форму всех записей для редактирования
    @GetMapping(path="/parkingEdit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getFormEditParking(Model model) {
        parkingService.findAllParking(ActionFront.EDIT, model);
        return "parking/parking";
    }

    //Получить форму для редактирования по айди
    @GetMapping(path="/parkingEdit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getFormEditParkingById(@PathVariable(value = "id") int idParking, Model model) {
        parkingService.findParkingById(idParking, ActionFront.EDIT, model);
        return "parking/parking";
    }

    //Обработка изменения данных из формы
    @PostMapping(path="/parkingEdit")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String putParkingById(@RequestParam int idParking, @RequestParam int idClient,
                                 @RequestParam int idAuto, @RequestParam int idLot, @RequestParam int lotItem,
                                 @RequestParam String dateParking, @RequestParam String dateDepart,
                                 @RequestParam(value = "paid", required = false) String paid, Model model) throws InternalException {
        parkingService.editParkingById(idParking, idClient, idAuto, idLot, lotItem, dateParking,
                dateDepart, paid, model);
        return "parking/parking";
    }

    //Изменение данных происходит при передачи айди по ссылке
    @PostMapping(path="/parkingEdit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String putParkingByPathId(@PathVariable(value = "id") int idParking, @RequestParam int idClient,
                                     @RequestParam int idAuto, @RequestParam int idLot, @RequestParam int lotItem,
                                     @RequestParam String dateParking, @RequestParam String dateDepart,
                                     @RequestParam(value = "paid", required = false) String paid, Model model)
            throws InternalException
    {
        parkingService.editParkingById(idParking, idClient, idAuto, idLot, lotItem, dateParking,
                dateDepart, paid, model);
        return "redirect:/parkingEdit";
    }

    //Получить форму всех записей на удаление
    @GetMapping(path="/parkingDelete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllDeleteParking (Model model) {
        parkingService.findAllParking(ActionFront.DELETE, model);
        return "parking/parking";
    }

    //Получить форму с заданным айди на удаление
    @GetMapping(path="/parkingDelete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getToDeleteParking (@PathVariable(value = "id") int idParking, Model model) {
        parkingService.findParkingById(idParking, ActionFront.DELETE, model);
        return "parking/parking";
    }

    //Обработка удаления
    @PostMapping(path="/parkingDelete" )
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteParkingById(@RequestParam int idParking, Model model) {
        parkingService.deleteParkingById(idParking, model);
        return "parking/parking";
    }

    //Обработка удаления по айди
    @PostMapping(path="/parkingDelete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteParkingByPathId(@PathVariable(value = "id") int idParking, Model model) {
        parkingService.deleteParkingById(idParking, model);
        return "parking/parking";
    }

    //Получение формы всех записей
    @GetMapping(path="/parkingAll")
    public String getAllParking (Model model) {
        parkingService.findAllParking(ActionFront.ALL, model);
        return "parking/parking";
    }

    //Фильтрация данных на форме
    @PostMapping("/parkingAll")
    public String filter (@RequestParam int idClient, @RequestParam int idAuto,
                          @RequestParam int idLot, Model model)
    {
        parkingService.getParkingByPrimaryKeys(idClient, idAuto, idLot, ActionFront.ALL, model);
        return "parking/parking";
    }
}