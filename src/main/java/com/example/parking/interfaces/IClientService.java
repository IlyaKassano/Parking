package com.example.parking.interfaces;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public interface IClientService {
    String showAllClients ();
    String addNewClient (String fio, Optional<String> telephone, HttpServletResponse response);
    String getToEditClient (Map<String, Object> model);
    String getOneToEditClient (int idClient, Map<String, Object> model);
    String editClient (int idClient, Map<String, Object> model);
    String editByIdClient (int idClient, String fio, Optional<String> telephone, Map<String, Object> model, HttpServletResponse response);
    String getAllDeleteClient (Map<String, Object> model);
    String getToDeleteClient (int idClient, Map<String, Object> model);
    String deleteClient (int idClient, Map<String, Object> model);
    String deleteClientWithId (int idClient, Map<String, Object> model);
    String getAllClient (Map<String, Object> model);
    String filter (String fio, Map<String, Object> model);
}
