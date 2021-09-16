package com.example.parking.interfaces;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface IClientService {
    void addNewClient (String fio, Optional<String> telephone, HttpServletResponse response);
    void findAllClient (Model model);
    void findClientById (int idClient, Model model);
    void editClientById ( int idClient, String fio, Optional<String> telephone,
                        Model model, final HttpServletResponse response);
    void deleteClientById (int idClient, Model model);
    void getClientByFio (String fio, Model model);
}
