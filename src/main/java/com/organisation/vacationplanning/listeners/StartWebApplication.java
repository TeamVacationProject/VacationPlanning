package com.organisation.vacationplanning.listeners;

import com.organisation.vacationplanning.testingdata.TestData;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebListener
public class StartWebApplication implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    /*
    Инициализация и закрытие ConnectionPool
    */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        TestData testData = new TestData();
        testData.addTestingEmployee();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }
}
