package com.example.parking.services;

import com.example.parking.entities.AutoEntity;
import com.example.parking.entities.ClientEntity;
import com.example.parking.entities.ParkingEntity;
import com.example.parking.entities.ParkingLotEntity;
import com.example.parking.enums.ActionFront;
import com.example.parking.fronttemplates.ActionTemplate;
import com.example.parking.interfaces.IParkingService;
import com.example.parking.repos.AutoRepository;
import com.example.parking.repos.ClientRepository;
import com.example.parking.repos.ParkingLotRepository;
import com.example.parking.repos.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingService implements IParkingService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AutoRepository autoRepository;
    @Autowired
    private ParkingLotRepository lotRepository;
    @Autowired
    private ParkingRepository parkingRepository;

    public void getForm(ActionFront act, Model model) {
        ActionTemplate action = new ActionTemplate(act);
        if(act == ActionFront.DELETE)
            action.setReadonly(true);

        model.addAttribute("action", action);
    }

    public void addNewParking (int idClient, int idAuto, int idLot, int lotItem,
                           String dateParking, String dateDepart, String paid,
                           final HttpServletResponse response) {
        //Связанные сущности для вставки в таблицу
        ClientEntity client = clientRepository.findById(idClient).orElseThrow();
        AutoEntity auto = autoRepository.findById(idAuto).orElseThrow();
        ParkingLotEntity lot = lotRepository.findById(idLot).orElseThrow();

        ParkingEntity p = new ParkingEntity();
        insertToRepo(p, client, auto, lot, lotItem, dateParking, dateDepart, paid, response);
    }

    public void findAllParking (ActionFront act, Model model) {
        getForm(act, model);
        putEntitiesToModel(model);
    }

    public void findParkingById (int idParking, ActionFront act, Model model) {
        Iterable<ClientEntity> clients = clientRepository.findAll();
        Iterable<AutoEntity> autos = autoRepository.findAll();
        Iterable<ParkingLotEntity> lots = lotRepository.findAll();
        List<ParkingEntity> parkings = parkingRepository.findByIdParking(idParking);

        getForm(act, model);
        model.addAttribute("clients", clients);
        model.addAttribute("autos", autos);
        model.addAttribute("lots", lots);
        model.addAttribute("parkings", parkings);
    }

    public void editParkingById (int idParking, int idClient, int idAuto, int idLot, int lotItem,
                                 String dateParking, String dateDepart, String paid,
                                 Model model, HttpServletResponse response) {
        ParkingEntity p = parkingRepository.findById(idParking).orElseThrow();
        ClientEntity client = clientRepository.findById(idClient).orElseThrow();
        AutoEntity auto = autoRepository.findById(idAuto).orElseThrow();
        ParkingLotEntity lot = lotRepository.findById(idLot).orElseThrow();

        insertToRepo(p, client, auto, lot, lotItem, dateParking, dateDepart, paid, response);
        putEntitiesToModel(model);
    }

    public void deleteParkingById (int idParking, Model model) {
        ParkingEntity a = parkingRepository.findById(idParking).orElseThrow();
        try {
            parkingRepository.delete(a);
        }
        catch(Exception e) {
            model.addAttribute("err", "Произошла ошибка при удалении! Скорее всего удалению препятсвутют связи.");
        }

        findAllParking(ActionFront.DELETE, model);
    }

    public void getParkingByPrimaryKeys (int idClient, int idAuto, int idLot, ActionFront act, Model model)
    {
        //Связанные сущности
        ClientEntity clients = clientRepository.findById(idClient).orElseThrow();
        AutoEntity autos = autoRepository.findById(idAuto).orElseThrow();
        ParkingLotEntity lots = lotRepository.findById(idLot).orElseThrow();
        List<ParkingEntity> parkings = parkingRepository.findByClientByIdClientAndAutoByIdCarAndParkingLotByIdLot(
                clients, autos, lots
        );

        getForm(act, model);
        model.addAttribute("clients", clients);
        model.addAttribute("autos", autos);
        model.addAttribute("lots", lots);
        model.addAttribute("parkings", parkings);
    }

    private void putEntitiesToModel(Model model){
        Iterable<ClientEntity> clients = clientRepository.findAll();
        Iterable<AutoEntity> autos = autoRepository.findAll();
        Iterable<ParkingLotEntity> lots = lotRepository.findAll();
        Iterable<ParkingEntity> parkings = parkingRepository.findAll();

        model.addAttribute("clients", clients);
        model.addAttribute("autos", autos);
        model.addAttribute("lots", lots);
        model.addAttribute("parkings", parkings);
    }

    /**
     * Добавление или редактирование данных в репозитории
     * @param p Сущность парковочного места
     * @param client Сущность клиента
     * @param auto Сущность автообиля
     * @param lot Сущность парковочного места
     * @param lotItem Номер места
     * @param dateParking Дата парковки
     * @param dateDepart Дата убытия
     * @param paid Оплачено
     */
    private void insertToRepo(ParkingEntity p, ClientEntity client, AutoEntity auto, ParkingLotEntity lot,
                              int lotItem, String dateParking, String dateDepart, String paid,
                              HttpServletResponse response) {

        //Дата возвращается в формате строки, поэтому приходится дополнительно парсить строку с датой
        LocalDateTime dateStart = LocalDateTime.parse(dateParking);
        //Может вернуться пустой
        LocalDateTime dateEnd;
        long hours;

        //CheckBox возращает строку, а база требует byte
        byte pay;
        if (paid == null)
            pay = 0;
        else
            pay = 1;

        try {
            p.setClientByIdClient(client);
            p.setAutoByIdCar(auto);
            p.setParkingLotByIdLot(lot);
            p.setLotItem(lotItem);
            p.setDateParking(dateStart);
            if (dateDepart == null || dateDepart == "") {
                p.setDateDepart(null);
            } else {
                dateEnd = LocalDateTime.parse(dateDepart);
                p.setDateDepart(dateEnd);

                hours = Duration.between(dateStart, dateEnd).toSeconds() / 60 / 60;
                BigDecimal bigHours = new BigDecimal(hours);

                //Уножение часов на цену для получения стоимости
                p.setCost(bigHours.multiply(p.getParkingLotByIdLot().getPrice()));
            }
            p.setPaid(pay);

            parkingRepository.save(p);
        }
        catch(Exception e) {
            try {
                response.sendError(461);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public boolean checkValidLotItem(int idLot, int lotItem) {
        ParkingLotEntity lot = lotRepository.findById(idLot).orElseThrow();
        List<ParkingEntity> p = parkingRepository.findByParkingLotByIdLotAndLotItemAndDateDepartIsNull(lot, lotItem);

        return p.size() == 0 && lot.getNumLots() >= lotItem;
    }
}