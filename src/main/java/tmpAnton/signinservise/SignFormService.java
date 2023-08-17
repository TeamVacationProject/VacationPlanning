package tmpAnton.signinservise;

import com.organisation.vacationplanning.services.IVacationController;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import tmpAnton.HashingBcrypt;
import tmpAnton.cookieservise.ControlValidated;
import tmpAnton.cookieservise.TokensUserBD;
import tmpAnton.cookieservise.TokensUserDAO;

import java.io.IOException;
import java.io.Writer;
import java.util.UUID;

public class SignFormService implements IVacationController {

    private WebContext ctx;
    private final RegisteredUsersDAO usersDAO = new RegisteredUsersDAO();
    private final TokensUserDAO tokensUserDAO = new TokensUserDAO();
    private final HashingBcrypt bcrypt = new HashingBcrypt();
    private final ControlValidated controlValidated = new ControlValidated();
    private String csrfToken = "";

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, HttpServletResponse response) throws Exception {

        ctx = new WebContext(webExchange, webExchange.getLocale());

        if (webExchange.getRequest().getMethod().equals("GET")) {

            csrfToken = generateCSRFToken();
            ctx.setVariable("csrfToken", csrfToken);
            webExchange.getSession().setAttributeValue("csrfToken", csrfToken);

            templateEngine.process("signform", ctx, writer);

        } else if (webExchange.getRequest().getMethod().equals("POST")) {

            handlePost(webExchange, templateEngine, writer, response);//ToDo метод может только так называться?
        }

    }

    private void handlePost(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, HttpServletResponse response) throws IOException {

        String login = webExchange.getRequest().getParameterValue("login");
        String email = webExchange.getRequest().getParameterValue("email");

        String storedToken = (String) webExchange.getSession().getAttributeValue("csrfToken");
        String requestToken = webExchange.getRequest().getParameterValue("csrfToken");

        //ToDo добавить капчу https://www.tune-it.ru/web/marina/blog/-/blogs/16582509

        if (storedToken != null && storedToken.equals(requestToken) && requestToken.equals(csrfToken)) {
            //ToDo проверка на корректность ввода
            if (email != null) {
                String hashPassword = bcrypt.getHashPassword(webExchange.getRequest().getParameterValue("password"));
                usersDAO.createUser(login, email, hashPassword);
            } else if (login != null) {
                //ToDo ПРОВЕРИТЬ СООТВЕТСТВИЕ ПОЛЕЙ КЛАССА И БД!!!!!!!!!!!!!!!!!!!!!
                RegisteredUsersBD user = usersDAO.findUser(login, webExchange.getRequest().getParameterValue("password"));
                if (user != null) {

                    String d = "";

                    if (!(user.getTokensUserBD().getUuid() == null)) {
                        tokensUserDAO.updateTokenUser(login);
                    } else {
                        tokensUserDAO.createToken(login);
                    }

                    controlValidated.createCookie(login, response);

                    response.sendRedirect("/calendar");

                } else {
                    //ToDo Неправильно введен логин или пароль!https://www.tune-it.ru/web/marina/blog/-/blogs/16582509
                }
            }
        } else {
            //ToDo CSRF-атака, обработайте ошибку
        }
        templateEngine.process("signform", ctx, writer);
    }

    private String generateCSRFToken() {
        return UUID.randomUUID().toString();
    }

}

