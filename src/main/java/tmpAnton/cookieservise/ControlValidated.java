package tmpAnton.cookieservise;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import tmpAnton.signinservise.UserRole;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ControlValidated {

    TokensUserDAO tokensUserDAO = new TokensUserDAO();

    public boolean checkValidate(Map<String, String[]> cookieMap) {
        String token = Arrays.toString(cookieMap.get("token"));
        String login = Arrays.toString(cookieMap.get("login"));
        LocalDateTime now = LocalDateTime.now();
        tokensUserDAO = new TokensUserDAO();
        TokensUserBD user = tokensUserDAO.findToken(login.replace("[", "").replace("]", ""));
        return now.isBefore(user.getDateTimeExpiresAt()) && user.getUuid().equals(token.replace("[", "").replace("]", ""));
    }

    public Map<String, String> getFullNameUser(Map<String, String[]> cookieMap) {

        String login = Arrays.toString(cookieMap.get("login"));
        TokensUserBD user = tokensUserDAO.findToken(login.replace("[", "").replace("]", ""));
        String firstName = user.getRegisteredUsersBD().getEmployee().getFirstName();
        String surname = user.getRegisteredUsersBD().getEmployee().getSurname();
        String patronymic = user.getRegisteredUsersBD().getEmployee().getPatronymic();

        user.getRegisteredUsersBD().getUserRole();

        Map<String, String> fullNameUser = new HashMap<>();
        fullNameUser.put("firstName", firstName);
        fullNameUser.put("surname", surname);
        fullNameUser.put("patronymic", patronymic);

        return fullNameUser;
    }

    public void logoutUser(HttpServletResponse response) {
        Cookie cookieLogin = new Cookie("login", "");
        Cookie cookieToken = new Cookie("token", "");
        cookieLogin.setMaxAge(0);
        cookieToken.setMaxAge(0);
        response.addCookie(cookieLogin);
        response.addCookie(cookieToken);
    }

    public boolean isAdmin(Map<String, String[]> cookieMap) {
        String login = Arrays.toString(cookieMap.get("login"));
        TokensUserBD user = tokensUserDAO.findToken(login.replace("[", "").replace("]", ""));
        UserRole userRole = user.getRegisteredUsersBD().getUserRole();
        return userRole.toString().equals("ADMIN");
    }

    public boolean isManager(Map<String, String[]> cookieMap) {
        String login = Arrays.toString(cookieMap.get("login"));
        TokensUserBD user = tokensUserDAO.findToken(login.replace("[", "").replace("]", ""));
        UserRole userRole = user.getRegisteredUsersBD().getUserRole();
        return userRole.toString().equals("MANAGER");
    }

    public void createCookie(String login, HttpServletResponse response) {
        Cookie cookieLogin = new Cookie("login", login);
        Cookie cookieToken = new Cookie("token", tokensUserDAO.findToken(login).getUuid());
        cookieLogin.setMaxAge(24 * 60 * 60);
        cookieToken.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieLogin);
        response.addCookie(cookieToken);
    }
}

//ToDo сделать сервлет и кнопку для выхода из приложения и удаления куки
//@WebServlet("/close")
//public class CloseTabServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            ControlValidated controlValidated = new ControlValidated();
//            controlValidated.logoutUser(response)
//    }
//}

