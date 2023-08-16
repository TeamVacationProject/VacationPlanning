package com.organisation.vacationplanning.services.department;

import com.organisation.vacationplanning.database.HibernateUtil;
import com.organisation.vacationplanning.database.entities.Employee;
import com.organisation.vacationplanning.services.IVacationController;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.List;

public class DepartmentSettings implements IVacationController {

    //Управление настройками департамента/организации/подразделения

    private static final String ALL_EMPLOYEES = "from Employee";
    private WebContext ctx;
    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, HttpServletResponse response) throws Exception {
        ctx = new WebContext(webExchange, webExchange.getLocale());
        if(webExchange.getRequest().getMethod().equals("POST")) {
            handlePost(webExchange);
            handleGet(templateEngine, writer, response);
        }
        if (webExchange.getRequest().getMethod().equals("GET")) {
            handleGet(templateEngine, writer, response);
        }
    }

    private void handlePost(IWebExchange webExchange) {
        String id;
        String surname;
        String firstName;
        String patronymic;
        if (webExchange.getRequest().containsParameter("employee-id")) {

/*            surname = webExchange.getRequest().getParameterValue("surname");
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

    private void handleGet(ITemplateEngine templateEngine, Writer writer, HttpServletResponse response) {
        List<Employee> employees;

        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Query<Employee> query = session.createQuery(ALL_EMPLOYEES, Employee.class);
            employees = query.getResultList();
            tx.commit();
        }
        ctx.setVariable("employees", employees);
        templateEngine.process("settings", ctx, writer);
    }
}
