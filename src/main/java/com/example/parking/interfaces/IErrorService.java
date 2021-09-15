package com.example.parking.interfaces;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IErrorService {
    public String handleError (HttpServletRequest request, Map<String, Object> model);
}
