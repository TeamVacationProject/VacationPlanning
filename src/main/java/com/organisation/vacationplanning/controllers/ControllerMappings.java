package com.organisation.vacationplanning.controllers;

import com.organisation.vacationplanning.services.IVacationController;
import com.organisation.vacationplanning.services.department.DepartmentSettings;
import org.thymeleaf.web.IWebRequest;

import java.util.HashMap;
import java.util.Map;

public class ControllerMappings {
    /*
    Класс для перенаправления поступающих от клиента запросов на соотвествующие сервисы
    Вносим изменения
    */
    private static final Map<String, IVacationController> controllersByURL;

    static {
        //Здесь будет инициализация сервисов
        controllersByURL = new HashMap<>();
        controllersByURL.put("/settings", new DepartmentSettings());
    }
    private ControllerMappings() {
        super();
    }
    public static IVacationController resolveControllerForRequest(final IWebRequest request) {
        final String path = request.getPathWithinApplication();
        return controllersByURL.get(path);
    }
}
