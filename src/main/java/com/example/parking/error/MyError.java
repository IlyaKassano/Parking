package com.example.parking.error;

import java.util.HashMap;
import java.util.Map;

//TODO Свое исключение???
public class MyError {
    private int status;
    private String header;
    private String description;

    // FIXME Использоать стандартные коды ошибок

    // FIXME Использовать два Map'а такая себе идея.
    //  Подумать над оптимизацией и стоит ли вернуть Map'ы в отдельный класс
    public static Map<Integer, String> headers = new HashMap<>(); {
        headers.put(0, "Неопознанная ошибка!");
        headers.put(404, "Ошибка 403");
        headers.put(404, "Ошибка 404");
        headers.put(405, "Ошибка 405");
        headers.put(460, "Ошибка вставки в базу данных.");
        headers.put(461, "Ошибка вставки в базу данных.");
        headers.put(500, "Ошибка 500");

    }
    public static Map<Integer, String> descriptions = new HashMap<>(); {
        descriptions.put(0, "Произошла необработанная ошибка!");
        descriptions.put(403, "Нет доступа к данному ресурсу!");
        descriptions.put(404, "Страница не найдена!");
        descriptions.put(405, "Произошла неизвестная ошибка.");
        descriptions.put(460, "Заданное парковочное место уже занято!");
        descriptions.put(461, "Не удалось добавить/изменить данные в базе данных.");
        descriptions.put(500, "Ошибка сервера. Обратитесь к разработчику для дальнейшего разбирательства.");
    }

    public MyError(int status) {
        this.status = status;
        this.header = headers.get(status);
        this.description = descriptions.get(status);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
