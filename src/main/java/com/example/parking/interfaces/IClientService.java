package com.example.parking.interfaces;

import com.example.parking.enums.ActionFront;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface IClientService {
    void getForm(ActionFront act, Model model);
    void addNewClient (String fio, Optional<String> telephone, HttpServletResponse response);
    void findAllClient (ActionFront act, Model model);
    void findClientById (int idClient, ActionFront act, Model model);
    void editClientById ( int idClient, String fio, Optional<String> telephone,
                        Model model, final HttpServletResponse response);
    void deleteClientById (int idClient, Model model);
    void getClientByFio (String fio, ActionFront act, Model model);
}
