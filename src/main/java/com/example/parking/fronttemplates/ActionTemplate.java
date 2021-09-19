package com.example.parking.fronttemplates;

import com.example.parking.enums.ActionFront;

/**
 * Класс для передачи значений в шаблонизатор
 */
//FIXME Имеется ли от него теперь смысл???
public class ActionTemplate {
    private ActionFront action;
    private boolean readonly;

    public ActionTemplate() {
    }

    public ActionTemplate(ActionFront action) {
        this.action = action;;
    }

    public String getAction() {
        return action.toString().toLowerCase();
    }

    public void setAction(ActionFront action) {
        this.action = action;
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }
}
