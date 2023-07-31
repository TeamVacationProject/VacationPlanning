package com.organisation.vacationplanning.services.department;

import com.organisation.vacationplanning.database.HibernateUtil;
import com.organisation.vacationplanning.database.entities.Employee;
import com.organisation.vacationplanning.services.IVacationController;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class CalendarService implements IVacationController {

    //Управление настройками департамента/организации/подразделения

    private static final String ALL_EMPLOYEES = "from Employee";
    private WebContext ctx;
    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        ctx = new WebContext(webExchange, webExchange.getLocale());
        if(webExchange.getRequest().getMethod().equals("POST")) {
            handlePost(webExchange);
            handleGet(templateEngine, writer);
        }
        if (webExchange.getRequest().getMethod().equals("GET")) {
            handleGet(templateEngine, writer);
        }
    }

    private void handlePost(IWebExchange webExchange) {
        String id;
        String surname;
        String firstName;
        String patronymic;
        if (webExchange.getRequest().containsParameter("employee-id")) {

/*          surname = webExchange.getRequest().getParameterValue("surname");
            firstName = webExchange.getRequest().getParameterValue("first-name");
            patronymic = webExchange.getRequest().getParameterValue("patronymic");*/

            try(Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();

                tx.commit();
            }
        } else {
/*            surname = webExchange.getRequest().getParameterValue("surname");
            firstName = webExchange.getRequest().getParameterValue("first-name");
            patronymic = webExchange.getRequest().getParameterValue("patronymic");
            Employee employee = new Employee();
            employee.setSurname(surname);
            employee.setFirstName(firstName);
            employee.setPatronymic(patronymic);*/
            try(Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction tx = session.beginTransaction();

                tx.commit();
            }
        }
    }

    private void handleGet(ITemplateEngine templateEngine, Writer writer) {
        List<Employee> employees;
        // Создание двумерного списка для представления календаря
        List<List<Integer>> weeks = new ArrayList<>();
        /*
        // Передача данных в шаблон
        model.addAttribute("weeks", weeks);
         */
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Получение текущей даты
            LocalDate currentDate = LocalDate.now();

            // Получение первого дня текущего месяца
            LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());



            // Заполнение списка днями месяца
            int day = 1;
            while (day <= currentDate.lengthOfMonth()) {
                List<Integer> week = new ArrayList<>();
                for (int i = 1; i < 8; i++) {
                    if ((firstDayOfMonth.getDayOfWeek().getValue() == i || day > 1) && day <= currentDate.lengthOfMonth()) {
                        week.add(day);
                        day++;
                    } else {
                        week.add(null);
                    }
                }
                weeks.add(week);
            }
        }
        ctx.setVariable("weeks", weeks);
        templateEngine.process("calendar", ctx, writer);
    }
}
