package com.example.parking.repos;

import com.example.parking.entities.AutoEntity;
import com.example.parking.entities.ClientEntity;
import com.example.parking.entities.ParkingEntity;
import com.example.parking.entities.ParkingLotEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ParkingRepository extends CrudRepository<ParkingEntity, Integer> {

    List<ParkingEntity> findByIdParking(int idParking);
    List<ParkingEntity> findByClientByIdClientAndAutoByIdCarAndParkingLotByIdLot(ClientEntity client, AutoEntity auto, ParkingLotEntity lot);
    List<ParkingEntity> findByParkingLotByIdLotAndLotItemAndDateDepartIsNull(ParkingLotEntity lot, int lotItem);
}