package com.example.parking.interfaces;

import org.springframework.ui.Model;

public interface IRegistrationService {
    boolean putRegistration(String username, String password, Model model);
}
