package com.organisation.vacationplanning;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebFilter(filterName = "MainFilter")
public class MainFilter implements Filter {

    /*
    Здесь будет настройка Thymeleaf и создание ControllerMappings
    */


/*
Привет мир
*/
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
    }
}
