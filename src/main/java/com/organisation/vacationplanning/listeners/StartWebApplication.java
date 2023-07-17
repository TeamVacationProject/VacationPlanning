package com.organisation.vacationplanning.listeners;

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
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }
}
