package com.organisation.vacationplanning.services.auth;

import com.organisation.vacationplanning.database.HibernateUtil;
import com.organisation.vacationplanning.services.IVacationController;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import tmpAnton.HashingBcrypt;
import tmpAnton.TmpEmployeePassBD;

import java.io.Writer;

public class AuthService implements IVacationController {
    private WebContext ctx;
    private static final String ALL_EMPLOYEES = "from Employee";
    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        ctx = new WebContext(webExchange, webExchange.getLocale());
        if (webExchange.getRequest().getMethod().equals("GET")) {
            handleGet(templateEngine, writer);
        }
    }

    private void handleGet(ITemplateEngine templateEngine, Writer writer) {
        HashingBcrypt bcrypt = new HashingBcrypt();
        String pass = bcrypt.getHashPassword("www");
/*        List<Employee> employees;*/
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

          TmpEmployeePassBD tmpEmployeePassBD = new TmpEmployeePassBD("ttt", pass, "email4");
          session.persist(tmpEmployeePassBD);

            tx.commit();
        }
/*        ctx.setVariable("employees", employees);
        templateEngine.process("settings", ctx, writer);*/
    }
}
