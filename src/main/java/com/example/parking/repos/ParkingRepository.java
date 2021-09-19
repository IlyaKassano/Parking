package com.example.parking.repos;

import com.example.parking.entities.AutoEntity;
import com.example.parking.entities.ClientEntity;
import com.example.parking.entities.ParkingEntity;
import com.example.parking.entities.ParkingLotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ParkingRepository extends JpaRepository<ParkingEntity, Integer> {

    List<ParkingEntity> findByIdParking(int idParking);

    List<ParkingEntity> findByClientByIdClientAndAutoByIdCarAndParkingLotByIdLot(ClientEntity client, AutoEntity auto, ParkingLotEntity lot);

    List<ParkingEntity> findByParkingLotByIdLotAndLotItemAndDateDepartIsNull(ParkingLotEntity lot, int lotItem);

    /*
    @Query("SELECT p.idParking as idParking, p.clientByIdClient, p.autoByIdCar, p.parkingLotByIdLot, p.lotItem, " +
            "p.dateParking, p.dateDepart, (p.dateDepart - p.dateParking) / 100 / 60 * p.parkingLotByIdLot.price " +
            "FROM ParkingEntity p ")
    List<Object> findAllWithCost();

    @Query("SELECT p.idParking as idParking, p.clientByIdClient, p.autoByIdCar, p.parkingLotByIdLot, p.lotItem, " +
            "p.dateParking, p.dateDepart, (p.dateDepart - p.dateParking) / 100 / 60 * p.parkingLotByIdLot.price " +
            "FROM ParkingEntity p " +
            "WHERE p.clientByIdClient.idClient = ?1 AND p.autoByIdCar.idAuto = ?2 AND p.parkingLotByIdLot.idLot = ?3")
    List<Object> findByPrimaryCodesWithCost(int idClient, int idAuto, int idLot);


    @Query("SELECT p.id_parking, CONCAT(c.last_name, ' ', c.first_name, ' ', c.second_name) as 'client', " +
            "CONCAT(a.brand, ' ', a.model) as 'auto', pl.name as 'parkingLot', p.lot_item, p.date_parking, " +
            "p.date_depart, (FORMAT((p.date_depart - p.date_parking) / 100 / 100, 2)) as 'cost' " +
            "FROM parking p " +
            "INNER JOIN client c ON p.id_client = c.id_client " +
            "INNER JOIN auto a ON p.id_car = a.id_auto " +
            "INNER JOIN parking_lot pl ON p.id_lot = pl.id_lot " +
            "WHERE p.id_client = ?1 AND p.id_car = ?2 AND p.id_lot = ?3")
    List<ParkingEntity> findByPrimaryCodesWithCost(int idClient, int idAuto, int idLot);*/
}
