package com.example.parking.repos;

import com.example.parking.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

    public interface ClientRepository extends JpaRepository<ClientEntity, Integer> {

    List<ClientEntity> findByIdClient(int idClient);
    //TODO Изменить точный поиск на примерный?
    List<ClientEntity> findByLastNameContainsAndFirstNameContainsAndSecondNameContainsAllIgnoreCase(String lastName, String firstName, String secondName);
    List<ClientEntity> findByLastNameContainsAndFirstNameContainsAllIgnoreCase(String lastName, String firstName);
    List<ClientEntity> findByLastNameContainsIgnoreCase(String lastName);
}