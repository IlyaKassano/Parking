package com.example.parking.repos;

import com.example.parking.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByIdUser(int idUser);
    List<UserEntity> findByUsername(String username);
}
