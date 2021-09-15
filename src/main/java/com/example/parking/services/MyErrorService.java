package com.example.parking.services;

import com.example.parking.error.MyError;
import com.example.parking.interfaces.IErrorService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class MyErrorService implements ErrorController, IErrorService {

    public String handleError(HttpServletRequest request, Map<String, Object> model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                putErrorToModel(404, model);
                return "error";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                putErrorToModel(405, model);
                return "error";
            }
            else if(statusCode == 460) {
                putErrorToModel(460, model);
                return "error";
            }
            else {
                putErrorToModel(0, model);
                return "error";
            }
        }
        return "error";
    }

    private void putErrorToModel(int status, Map<String, Object> model) {
        MyError err = new MyError(status);
        model.put("err", err);
    }

    public String getErrorPath() {
        return "error";
    }
}
