package tmpAnton.signinservise;

import com.organisation.vacationplanning.database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tmpAnton.HashingBcrypt;

import java.util.List;

public class RegisteredUsersDAO {

    HashingBcrypt bcrypt = new HashingBcrypt();

    public void createUser(String login, String email, String hashPassword) {


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            RegisteredUsersBD registeredUsersBD = new RegisteredUsersBD(login, email, hashPassword);
            session.persist(registeredUsersBD);

            session.flush();
            tx.commit();
        }
    }

    public RegisteredUsersBD findUser(String login, String password) {

        RegisteredUsersBD user;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {

                user = (RegisteredUsersBD) session.createQuery("from RegisteredUsersBD e where e.login = :login")
                        .setParameter("login", login).getSingleResult();

            } catch (Exception e) {
                return null;
            }
            session.flush();
            tx.commit();
        }
        if (bcrypt.checkPassword(password, user.getEmail())) {
            return user;
        } else {
            return null;
        }
    }
}
