package com.example.parking.repos;

import com.example.parking.entities.AutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AutoRepository extends JpaRepository<AutoEntity, Integer> {

    List<AutoEntity> findByIdAuto(int idAuto);
    List<AutoEntity> findByBrandAndModel(String brand, String autoModel);
    /*List<AutoEntity> findByLastNameAndFirstNameAndSecondNameAllIgnoreCase(String lastName, String firstName, String secondName);
    List<AutoEntity> findByLastNameAndFirstNameAllIgnoreCase(String lastName, String firstName);
    List<AutoEntity> findByLastNameIgnoreCase(String lastName);*/
}