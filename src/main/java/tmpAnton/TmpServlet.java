package tmpAnton;

import com.organisation.vacationplanning.services.IVacationController;
import jakarta.persistence.Column;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import tmpAnton.cookieservise.TokensUserBD;
import tmpAnton.cookieservise.TokensUserDAO;

import java.awt.*;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@WebServlet("/test")
public class TmpServlet extends HttpServlet implements IVacationController {

    TmpEmployeePassDAO dao = new TmpEmployeePassDAO();
    private WebContext ctx;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String id = request.getParameter("id");
//        String login = request.getParameter("login");
//        String HASH_PASSWORD = request.getParameter("HASH_PASSWORD");
//        String E_MAIL = request.getParameter("E_MAIL");
//
//        dao.createUser(id, login, HASH_PASSWORD, E_MAIL);

    }

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        ctx = new WebContext(webExchange, webExchange.getLocale());

        if (webExchange.getRequest().getMethod().equals("GET")) {


            String login = (String) webExchange.getSession().getAttributeValue("login");
            String token = (String) webExchange.getSession().getAttributeValue("token");
            TokensUserDAO tokensUserDAO = new TokensUserDAO();
            TokensUserBD user = tokensUserDAO.findToken(login);
            LocalDateTime now = LocalDateTime.now();

            if (user == null) {
                Desktop d = Desktop.getDesktop();
                try {
                    d.browse(new URI("http://localhost:8080/signform"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            } else if (now.isBefore(user.getDateTimeExpiresAt()) && user.getUuid().equals(token)) {
                templateEngine.process("available", ctx, writer);
            } else {
                templateEngine.process("unavailable", ctx, writer);
            }



//            String id = webExchange.getRequest().getParameterValue("id");
//            String E_MAIL = webExchange.getRequest().getParameterValue("E_MAIL");
//            String HASH_PASSWORD = webExchange.getRequest().getParameterValue("HASH_PASSWORD");
//            String login = webExchange.getRequest().getParameterValue("login");
//
//            dao.createUser(id, E_MAIL, HASH_PASSWORD, login);
//
////            http://localhost:8080/VacationPlanning_war_exploded/test?id=4&E_MAIL=tt&HASH_PASSWORD=yy&login=uuu
        }
    }
}
