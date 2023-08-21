package tmpAnton.cookieservise;

import com.github.cage.GCage;
import com.organisation.vacationplanning.services.IVacationController;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;

public class LogoutUser implements IVacationController {

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, HttpServletResponse response) throws Exception {

        if (webExchange.getRequest().getMethod().equals("GET")) {

            ControlValidated controlValidated = new ControlValidated();
            controlValidated.logoutUser(response);
            response.sendRedirect("/signform");

        }
    }
}
