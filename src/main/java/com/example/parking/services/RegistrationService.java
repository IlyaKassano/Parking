package com.example.parking.services;

import com.example.parking.enums.Role;
import com.example.parking.entities.UserEntity;
import com.example.parking.interfaces.IRegistrationService;
import com.example.parking.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;

@Service
public class RegistrationService implements IRegistrationService {

    @Autowired
    private UserRepository userRepository;

    public boolean putRegistration(String username, String password, Model model){
        UserEntity user = userRepository.findByUsername(username);

        if(user != null) {
            model.addAttribute("message", "Пользователь уже существует!");
            return false;
        }

        user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setEnabled((byte) 1);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return true;
    }
}
