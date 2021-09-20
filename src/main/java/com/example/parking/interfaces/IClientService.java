package com.example.parking.interfaces;

import com.example.parking.enums.ActionFront;
import com.example.parking.exception.InternalException;
import org.springframework.ui.Model;

import java.util.Optional;

public interface IClientService {
    void getForm(ActionFront act, Model model);
    void addNewClient (String fio, Optional<String> telephone) throws InternalException;
    void findAllClient (ActionFront act, Model model);
    void findClientById (int idClient, ActionFront act, Model model);
    void editClientById ( int idClient, String fio, Optional<String> telephone, Model model)
            throws InternalException;
    void deleteClientById (int idClient, Model model);
    void getClientByFio (String fio, ActionFront act, Model model);
}
