package com.example.parking.repos;

import com.example.parking.entities.ParkingLotEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ParkingLotRepository extends CrudRepository<ParkingLotEntity, Integer> {

    List<ParkingLotEntity> findByIdLot(int idLot);
    List<ParkingLotEntity> findByName(String name);
}