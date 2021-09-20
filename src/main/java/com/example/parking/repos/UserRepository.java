package com.example.parking.repos;

import com.example.parking.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByIdUser(int idUser);
    UserEntity findByUsername(String username);
}
