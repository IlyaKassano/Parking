package com.example.parking.repos;

import org.springframework.data.repository.CrudRepository;

import com.example.parking.domain.ClientEntity;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {

}