package com.example.parking.repos;

import com.example.parking.entities.ParkingLotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ParkingLotRepository extends JpaRepository<ParkingLotEntity, Integer> {

    List<ParkingLotEntity> findByIdLot(int idLot);
    List<ParkingLotEntity> findByName(String name);
}