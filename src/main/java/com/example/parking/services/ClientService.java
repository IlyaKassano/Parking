package com.example.parking.services;

import com.example.parking.entities.ClientEntity;
import com.example.parking.enums.ActionFront;
import com.example.parking.exception.InternalException;
import com.example.parking.fronttemplates.ActionTemplate;
import com.example.parking.interfaces.IClientService;
import com.example.parking.repos.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    public void addNewClient (String fio, Optional<String> telephone) throws InternalException {
        ClientEntity client = new ClientEntity();
        insertToRepo(client, fio, telephone);
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

    public void editClientById ( int idClient, String fio, Optional<String> telephone, Model model)
            throws InternalException
    {
        ClientEntity client = clientRepository.findById(idClient).orElseThrow();
        insertToRepo(client, fio, telephone);

        findAllClient(ActionFront.EDIT, model);
    }

    public void deleteClientById (int idClient, Model model) {
        ClientEntity a = clientRepository.findById(idClient).orElseThrow();
        try {
            clientRepository.delete(a);
        }
        catch(Exception e) {
            model.addAttribute("err", "Произошла ошибка при удалении! Скорее всего удалению препятсвутют связи.");
        }

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
     * Добавление или редактирование данных в репозитории
     * @param c Сущность клиента
     * @param fio ФИО клиента
     * @param telephone Телефон клиента
     */
    public void insertToRepo(ClientEntity c, String fio, Optional<String> telephone) throws InternalException {
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
            throw new InternalException(500, "Ошибка при добавлении/редактировании данных.");
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