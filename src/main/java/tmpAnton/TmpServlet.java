package tmpAnton;

import com.organisation.vacationplanning.services.IVacationController;
import jakarta.persistence.Column;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.web.IWebExchange;

import java.io.IOException;
import java.io.Writer;

@WebServlet("/test")
public class TmpServlet extends HttpServlet implements IVacationController {

    TmpEmployeePassDAO dao = new TmpEmployeePassDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String login = request.getParameter("login");
        String HASH_PASSWORD = request.getParameter("HASH_PASSWORD");
        String E_MAIL = request.getParameter("E_MAIL");

        dao.createUser(id, login, HASH_PASSWORD, E_MAIL);

    }

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        if (webExchange.getRequest().getMethod().equals("GET")) {

            String id = webExchange.getRequest().getParameterValue("id");
            String login = webExchange.getRequest().getParameterValue("login");
            String HASH_PASSWORD = webExchange.getRequest().getParameterValue("HASH_PASSWORD");
            String E_MAIL = webExchange.getRequest().getParameterValue("E_MAIL");

            dao.createUser(id, login, HASH_PASSWORD, E_MAIL);
        }
    }
}
