package com.example.parking.services;

import com.example.parking.entities.AutoEntity;
import com.example.parking.entities.ClientEntity;
import com.example.parking.entities.ParkingEntity;
import com.example.parking.entities.ParkingLotEntity;
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

    public void addNewParking (int idClient, int idAuto, int idLot, int lotItem,
                           String dateParking, String dateDepart, String paid,
                           final HttpServletResponse response) {
        //Связанные сущности для вставки в таблицу
        ClientEntity client = clientRepository.findById(idClient).orElseThrow();
        AutoEntity auto = autoRepository.findById(idAuto).orElseThrow();
        ParkingLotEntity lot = lotRepository.findById(idLot).orElseThrow();

        ParkingEntity p = new ParkingEntity();
        insertToDb(p, client, auto, lot, lotItem, dateParking, dateDepart, paid, response);
    }

    public void findAllParking (Model model) {
        putEntitiesToModel(model);
    }

    public void findParkingById (int idParking, Model model) {
        Iterable<ClientEntity> clients = clientRepository.findAll();
        Iterable<AutoEntity> autos = autoRepository.findAll();
        Iterable<ParkingLotEntity> lots = lotRepository.findAll();
        List<ParkingEntity> parking = parkingRepository.findByIdParking(idParking);

        model.addAttribute("clients", clients);
        model.addAttribute("autos", autos);
        model.addAttribute("lots", lots);
        model.addAttribute("parking", parking);
    }

    public void editParkingById (int idParking, int idClient, int idAuto, int idLot, int lotItem,
                                 String dateParking, String dateDepart, String paid,
                                 Model model, HttpServletResponse response) {
        ParkingEntity p = parkingRepository.findById(idParking).orElseThrow();
        ClientEntity client = clientRepository.findById(idClient).orElseThrow();
        AutoEntity auto = autoRepository.findById(idAuto).orElseThrow();
        ParkingLotEntity lot = lotRepository.findById(idLot).orElseThrow();

        insertToDb(p, client, auto, lot, lotItem, dateParking, dateDepart, paid, response);
        putEntitiesToModel(model);
    }

    public void deleteParkingById (int idParking, Model model) {
        ParkingEntity a = parkingRepository.findById(idParking).orElseThrow();
        parkingRepository.delete(a);

        findAllParking(model);
    }
    
    public void getParkingByPrimaryCodes (int idClient, int idAuto,
                          int idLot, Model model)
    {
        //Связанные сущности
        ClientEntity clients = clientRepository.findById(idClient).orElseThrow();
        AutoEntity autos = autoRepository.findById(idAuto).orElseThrow();
        ParkingLotEntity lots = lotRepository.findById(idLot).orElseThrow();
        Iterable<ParkingEntity> parking = parkingRepository.findByClientByIdClientAndAutoByIdCarAndParkingLotByIdLot(clients, autos, lots);

        model.addAttribute("clients", clients);
        model.addAttribute("autos", autos);
        model.addAttribute("lots", lots);
        model.addAttribute("parking", parking);
    }

    private void putEntitiesToModel(Model model){
        Iterable<ClientEntity> clients = clientRepository.findAll();
        Iterable<AutoEntity> autos = autoRepository.findAll();
        Iterable<ParkingLotEntity> lots = lotRepository.findAll();
        Iterable<ParkingEntity> parking = parkingRepository.findAll();

        model.addAttribute("clients", clients);
        model.addAttribute("autos", autos);
        model.addAttribute("lots", lots);
        model.addAttribute("parking", parking);
    }

    private void insertToDb(ParkingEntity p, ClientEntity client, AutoEntity auto, ParkingLotEntity lot,
                            int lotItem, String dateParking, String dateDepart, String paid,
                            HttpServletResponse response) {

        //Дата возвращается в формате строки, поэтому приходится дополнительно парсить строку с датой
        LocalDateTime dateStart = LocalDateTime.parse(dateParking);
        //Может вернуться пустой
        LocalDateTime dateEnd;

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