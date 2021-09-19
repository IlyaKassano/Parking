package com.example.parking.services;

import com.example.parking.entities.ClientEntity;
import com.example.parking.enums.ActionFront;
import com.example.parking.fronttemplates.ActionTemplate;
import com.example.parking.interfaces.IClientService;
import com.example.parking.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {
    @Autowired
    private ClientRepository clientRepository;

    public void getForm(ActionFront act, Model model) {
        ActionTemplate action = new ActionTemplate(act);
        if(act == ActionFront.DELETE)
            action.setReadonly(true);

        model.addAttribute("action", action);
    }

    public void addNewClient (String fio, Optional<String> telephone, final HttpServletResponse response) {
        ClientEntity client = new ClientEntity();
        insertToDb(client, fio, telephone, response);
    }

    public void findAllClient (ActionFront act, Model model) {
        Iterable<ClientEntity> clients = clientRepository.findAll();

        getForm(act, model);
        model.addAttribute("clients", clients);
    }

    public void findClientById (int idClient, ActionFront act, Model model) {
        List<ClientEntity> clients = clientRepository.findByIdClient(idClient);

        getForm(act, model);
        model.addAttribute("clients", clients);
    }

    public void editClientById ( int idClient, String fio, Optional<String> telephone,
                                 Model model, HttpServletResponse response) {
        ClientEntity client = clientRepository.findById(idClient).orElseThrow();
        insertToDb(client, fio, telephone, response);

        findAllClient(ActionFront.EDIT, model);
    }

    public void deleteClientById (int idClient, Model model) {
        ClientEntity a = clientRepository.findById(idClient).orElseThrow();
        clientRepository.delete(a);

        findAllClient(ActionFront.DELETE, model);
    }

    public void getClientByFio (String fio, ActionFront act, Model model)
    {
        List<ClientEntity> clients;
        ArrayList<String> fio_arr = getFio(fio);

        if (fio_arr.size() > 2) {
            clients = clientRepository.findByLastNameContainsAndFirstNameContainsAndSecondNameContainsAllIgnoreCase(
                    fio_arr.get(0), fio_arr.get(1), fio_arr.get(2)
            );
        } else if (fio_arr.size() > 1) {
            clients = clientRepository.findByLastNameContainsAndFirstNameContainsAllIgnoreCase(
                    fio_arr.get(0), fio_arr.get(1)
            );
        } else {
            clients = clientRepository.findByLastNameContainsIgnoreCase(fio_arr.get(0));
        }

        getForm(act, model);
        model.addAttribute("clients", clients);
    }

    /**
     * Добавление или редактирование данных в базе
     * @param c Сущность клиента
     * @param fio ФИО клиента
     * @param telephone Телефон клиента
     * @param response Ответ для передачи ошибки
     */
    public void insertToDb(ClientEntity c, String fio, Optional<String> telephone, HttpServletResponse response){
        ArrayList<String> fio_arr = getFio(fio);
        //Вставка данных в БД
        try {
            c.setLastName(fio_arr.get(0));
            c.setFirstName(fio_arr.get(1));
            if (fio_arr.size() > 2)
                c.setSecondName(fio_arr.get(2));
            c.setTelephone(telephone.get());

            clientRepository.save(c);
        }
        catch(Exception e) {
            try {
                response.sendError(461);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * Разделение ФИО на части
     * @param fio Полное ФИО
     * @return Фамилия, имя, отчество
     */
    private ArrayList<String> getFio(String fio) {
        ArrayList<String> fio_arr = new ArrayList<String>(3);
        String[] fio_spl = fio.split(" ");
        fio_arr.addAll(Arrays.asList(fio_spl));
        return fio_arr;
    }
}