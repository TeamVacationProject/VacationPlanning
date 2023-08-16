package tmpAnton.signinservise;

import com.organisation.vacationplanning.services.IVacationController;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import tmpAnton.HashingBcrypt;
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

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, HttpServletResponse response) throws Exception {

        ctx = new WebContext(webExchange, webExchange.getLocale());

        if (webExchange.getRequest().getMethod().equals("GET")) {

            String csrfToken = generateCSRFToken();
            webExchange.getSession().setAttributeValue("csrfToken", csrfToken);

            templateEngine.process("signform", ctx, writer);

        } else if (webExchange.getRequest().getMethod().equals("POST")) {

            handlePost(webExchange, templateEngine, writer, response);//ToDo метод может только так называться?
        }

    }

    private void handlePost(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, HttpServletResponse response) throws IOException {

        String login = webExchange.getRequest().getParameterValue("login");
        String email = webExchange.getRequest().getParameterValue("email");
        //ToDo добавить в форму ключевое слово
        String storedToken = (String) webExchange.getSession().getAttributeValue("csrfToken");
        String requestToken = webExchange.getRequest().getParameterValue("csrfToken");

        if (storedToken != null && requestToken != null && storedToken.equals(requestToken)) {
            // Токен совпадает, продолжайте обработку запроса
            // ...
        } else {
            // CSRF-атака, обработайте ошибку
            // ...
        }

        //ToDo проверка на корректность ввода
        if (email != null) {
            String hashPassword = bcrypt.getHashPassword(webExchange.getRequest().getParameterValue("password"));
            usersDAO.createUser(login, email, hashPassword);
        } else if (login != null) {
            //ToDo ПРОВЕРИТЬ СООТВЕТСТВИЕ ПОЛЕЙ КЛАССА И БД!!!!!!!!!!!!!!!!!!!!!
            RegisteredUsersBD user = usersDAO.findUser(login, webExchange.getRequest().getParameterValue("password"));
            if (user != null) {

                TokensUserBD userToken = tokensUserDAO.findToken(login);
                if (!(userToken.getUuid() == null)) {
                    tokensUserDAO.updateTokenUser(login);
                }

                tokensUserDAO.createToken(login);

                Cookie cookieLogin = new Cookie("login", login);
                Cookie cookieToken = new Cookie("token", tokensUserDAO.findToken(login).getUuid());
                cookieLogin.setMaxAge(24 * 60 * 60);
                cookieToken.setMaxAge(24 * 60 * 60);
                response.addCookie(cookieLogin);
                response.addCookie(cookieToken);

                response.sendRedirect("/calendar");

            } else {
                //ToDo Неправильно введен логин или пароль!
            }
        }

        templateEngine.process("signform", ctx, writer);
    }

    private String generateCSRFToken() {
        return UUID.randomUUID().toString();

        //ToDo Создать БД Куки+CSRFToken -> в БД хранить название куки и токен -> куки отдать в форму -> после получения ответа формы сверить токен по имени Куки
        //ToDo сделать отдельный метод для проверок токенов -> Тру Фолс на продолжение работы
    }

}

