package tmpAnton.signinservise;

import com.organisation.vacationplanning.database.HibernateUtil;
import com.organisation.vacationplanning.services.IVacationController;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import tmpAnton.HashingBcrypt;
import tmpAnton.TmpEmployeePassBD;
import tmpAnton.cookieservise.TokensUserBD;
import tmpAnton.cookieservise.TokensUserDAO;

import java.awt.*;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;

public class SignFormService implements IVacationController {

    private WebContext ctx;
    private RegisteredUsersDAO usersDAO = new RegisteredUsersDAO();
    private TokensUserDAO tokensUserDAO = new TokensUserDAO();
    private HashingBcrypt bcrypt = new HashingBcrypt(); //ToDo нормально ли здесь делать хэш пароля?

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {

        ctx = new WebContext(webExchange, webExchange.getLocale());

        if (webExchange.getRequest().getMethod().equals("GET")) {

            templateEngine.process("signform", ctx, writer);

        } else if (webExchange.getRequest().getMethod().equals("POST")) {

            handlePost(webExchange, templateEngine, writer);//ToDo метод может только так называться?
        }

    }

    private void handlePost(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) {

        String login = webExchange.getRequest().getParameterValue("login");
        String email = webExchange.getRequest().getParameterValue("email");
//        String password = webExchange.getRequest().getParameterValue("password");

        //ToDo проверка на корректность ввода
        if (email != null) {
            String hashPassword = bcrypt.getHashPassword(webExchange.getRequest().getParameterValue("password"));
            usersDAO.createUser(login, email, hashPassword);
        } else if (login != null) {
            RegisteredUsersBD user = usersDAO.findUser(login, webExchange.getRequest().getParameterValue("password"));
            if (user != null) {

                TokensUserBD userToken = tokensUserDAO.findToken(login);
                if (!(userToken == null)) {
                    tokensUserDAO.deleteToken(login);
                }

                tokensUserDAO.createToken(login);
                webExchange.getSession().setAttributeValue("login", login);
                webExchange.getSession().setAttributeValue("token", tokensUserDAO.findToken(login).getUuid());
                //ToDo перебросить на страницу после регистрации

            } else {
                //ToDo Неправильно введен логин или пароль!
            }
        }

        templateEngine.process("signform", ctx, writer);
    }

}

//                templateEngine.process("calendar", ctx, writer);
