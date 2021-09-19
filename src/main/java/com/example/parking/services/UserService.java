package com.example.parking.services;

import com.example.parking.entities.UserEntity;
import com.example.parking.enums.ActionFront;
import com.example.parking.fronttemplates.ActionTemplate;
import com.example.parking.interfaces.IUserService;
import com.example.parking.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean addNewUser (String username, String password, byte enabled, Model model, final HttpServletResponse response) {
        List<UserEntity> usr = userRepository.findByUsername(username);

        if(usr.size() != 0) {
            model.addAttribute("message", "Такой пользователь уже существует!");
            return false;
        }

        UserEntity user = new UserEntity();
        insertToRepo(user, username, password, enabled, response);
        return true;
    }

    /**
     * Добавление или редактирование данных в репозитории
     * @param c Сущность пользователя
     * @param username Имя пользователя
     * @param password Пароль
     * @param enabled Включен ли пользователь
     */
    public void insertToRepo(UserEntity c, String username, String password, byte enabled, HttpServletResponse response){
        try {
            c.setUsername(username);
            c.setPassword(passwordEncoder.encode(password));
            c.setEnabled(enabled);

            userRepository.save(c);
        }
        catch(Exception e) {
            try {
                response.sendError(461);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}