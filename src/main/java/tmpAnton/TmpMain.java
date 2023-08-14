package tmpAnton;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.http.Cookie;
import tmpAnton.cookieservise.TokensUserBD;
import tmpAnton.cookieservise.TokensUserDAO;
import tmpAnton.signinservise.RegisteredUsersBD;
import tmpAnton.signinservise.RegisteredUsersDAO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

public class TmpMain {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        HashingBcrypt bcrypt = new HashingBcrypt();
        String password = "www";
        byte[] salt = BCrypt.with(new SecureRandom()).hash(6, password.getBytes(StandardCharsets.UTF_8));
        String pass = bcrypt.getHashPassword("www");

        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), pass);

//        Cookie cookie = new Cookie("v", "123");

//        System.out.println(result);

        String token = UUID.randomUUID().toString();

//        System.out.println(LocalDateTime.now().plusMinutes(15));
//        System.out.println(bcrypt.checkPassword(pass, pass));

        TokensUserDAO tokensUserDAO = new TokensUserDAO();
//        TokensUserBD ww = tokensUserDAO.findToken("ww");
//        TokensUserBD ww = tokensUserDAO.findTokenByID("1");
        LocalDateTime now = LocalDateTime.now();
//        System.out.println(ww.getLogin());
//        tokensUserDAO.deleteToken("ww");
        RegisteredUsersDAO registeredUsersDAO = new RegisteredUsersDAO();
        RegisteredUsersBD user = registeredUsersDAO.findUser("bb", "bb");
        String uuid = user.getTokensUserBD().getUuid();
        System.out.println(uuid);


        //CREATE
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//
//            TmpEmployeePassBD tmpEmployeePassBD = new TmpEmployeePassBD("ggg3", "pass2", "email9");
//            session.persist(tmpEmployeePassBD);
//
////            session.flush();
//            tx.commit();
//        }

        //CREATE
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//
//            TmpCookiesSessionBD tmpCookiesSessionBD = new TmpCookiesSessionBD("aaa", "sss", "22", "44");
//            session.persist(tmpCookiesSessionBD);
//
//            tx.commit();
//        }

        //READ and Check Password
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//            TmpEmployeePassBD tmpEmployeePassBD = session.get(TmpEmployeePassBD.class, 1);
//            tx.commit();
//
//            System.out.println("Start"); //ToDo почему ищет именно по ИД, как по другому столбцу?
//            System.out.println(tmpEmployeePassBD);
//            boolean d = bcrypt.checkPassword("www", tmpEmployeePassBD.getHashPassword());
//            System.out.println("Start check");
//            System.out.println(d);
//
//        }


        //UPDATE
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//            TmpEmployeePassBD tmpEmployeePassBD = session.get(TmpEmployeePassBD.class, 1);
//            tmpEmployeePassBD.setEmail("email1");
//            tx.commit();
//
//            System.out.println("Start");
//            System.out.println(tmpEmployeePassBD);
//
//        }

        //DELETE
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//
//            TmpEmployeePassBD tmpEmployeePassBD = session.get(TmpEmployeePassBD.class, 2);
//            session.delete(tmpEmployeePassBD);
//
//            tx.commit();
//        }

        //GET List obj
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//
//            List<TmpEmployeePassBD> all = session.createQuery("from TmpEmployeePassBD").getResultList(); // получить все данные из таблицы
//            // from TmpEmployeePassBD e where e.login = 'ttt' - получить данные по столбцу login
//            // from TmpEmployeePassBD e where e.login LIKE 'ttt%' - получить данные, которые содержат определенные символы
//            // from TmpEmployeePassBD e where e.login = :login - получить данные по определенному значение из нужного столбца
////            List<TmpEmployeePassBD> all = session.createQuery("from TmpEmployeePassBD e where e.login = :login").setParameter("login", "rrr").getResultList();
//
//            System.out.println(all);
//
//            tx.commit();
//        }

    }
}

