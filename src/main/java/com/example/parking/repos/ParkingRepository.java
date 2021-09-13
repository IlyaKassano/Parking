package com.example.parking.repos;

import com.example.parking.entities.ParkingEntity;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ParkingRepository extends CrudRepository<ParkingEntity, Integer> {

    //List<ParkingEntity> findByIdLot(int idLot);
    //List<ParkingEntity> findByAddress(String address);
}