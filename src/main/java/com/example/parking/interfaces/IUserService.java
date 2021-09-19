package com.example.parking.interfaces;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

public interface IUserService {
    boolean addNewUser (String username, String password, byte enabled, Model model, HttpServletResponse response);
}
