package tmpAnton.signinservise;

import com.organisation.vacationplanning.services.IVacationController;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import tmpAnton.HashingBcrypt;
import tmpAnton.cookieservise.ControlValidated;
import tmpAnton.cookieservise.TokensUserBD;
import tmpAnton.cookieservise.TokensUserDAO;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

public class SignFormService implements IVacationController {

    private WebContext ctx;
    private final RegisteredUsersDAO usersDAO = new RegisteredUsersDAO();
    private final TokensUserDAO tokensUserDAO = new TokensUserDAO();
    private final HashingBcrypt bcrypt = new HashingBcrypt();
    private final ControlValidated controlValidated = new ControlValidated();
    private String csrfToken = "";
    private BufferedImage imageM;
//    private final String FILE_TYPE = "png";

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, HttpServletResponse response) throws Exception {

        ctx = new WebContext(webExchange, webExchange.getLocale());

        if (webExchange.getRequest().getMethod().equals("GET")) {

            int width = 256;
            int height = 256;
            BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            File file = new File("simple-esign-en");
            ImageIO.write(newImage, "jpeg", file);

            imageM = ImageIO.read(file);

            csrfToken = generateCSRFToken();
            ctx.setVariable("csrfToken", csrfToken);
            webExchange.getSession().setAttributeValue("csrfToken", csrfToken);

            String captcha = generateCaptcha(5);
            Graphics g = imageM.getGraphics();
            g.setColor(new Color(0x00, 0x80, 0x00));
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.getFont().deriveFont(Font.PLAIN, 14f);
            g.drawString(captcha, 400, 88);
            g.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] imageData = baos.toByteArray();
            String base64Image = Base64.getEncoder().encodeToString(imageData);
//            ImageIO.write(imageM, "jpeg", baos);

//            ImageIO.write(image, "png", new File("new-file.png"));

//            ctx.setVariable("captcha", captcha);
//            ctx.setVariable("image", imageM);
            ctx.setVariable("base64Image", base64Image);
//            webExchange.getSession().setAttributeValue("captcha", captcha);
//            webExchange.getSession().setAttributeValue("image", imageM);
            webExchange.getSession().setAttributeValue("base64Image", base64Image);

            templateEngine.process("signform", ctx, writer);

        } else if (webExchange.getRequest().getMethod().equals("POST")) {

            handlePost(webExchange, templateEngine, writer, response);//ToDo метод может только так называться?
        }

    }

    private void handlePost(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, HttpServletResponse response) throws IOException {

        String storedToken = (String) webExchange.getSession().getAttributeValue("csrfToken");
        String requestToken = webExchange.getRequest().getParameterValue("csrfToken");



        String login = webExchange.getRequest().getParameterValue("login");
        String email = webExchange.getRequest().getParameterValue("email");


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

    private String generateCaptcha(int captchaLength) {
        String captcha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        while (stringBuilder.length() < captchaLength) {
            int index = (int) (random.nextFloat() * captcha.length());
            stringBuilder.append(captcha.charAt(index));
        }
        return stringBuilder.toString();
    }

}


//        String userCaptcha = webExchange.getRequest().getParameterValue("g-recaptcha-response");
//
//        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
//        String secret = "6LeX3b4nAAAAAMCrRZnvH9UmMW0VIBO5J0TjmD7l";
//        String key = "6LeX3b4nAAAAABd_BowZZnF2J0RnmWcpSjcXAyrF";
//        reCaptcha.setPrivateKey(secret);
//        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer("ip_user", key, userCaptcha);
//
//        if (reCaptchaResponse.isValid()) {
//            System.out.println();
//        } else {
//            System.out.println();
//        }
