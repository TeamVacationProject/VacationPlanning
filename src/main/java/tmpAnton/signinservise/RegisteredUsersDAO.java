package tmpAnton.signinservise;

import com.organisation.vacationplanning.database.HibernateUtil;
import com.organisation.vacationplanning.database.entities.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tmpAnton.HashingBcrypt;
import tmpAnton.cookieservise.TokensUserBD;

public class RegisteredUsersDAO {

    HashingBcrypt bcrypt = new HashingBcrypt();

    public void createUser(String login, String email, String hashPassword) {


        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            RegisteredUsersBD registeredUsersBD = new RegisteredUsersBD(login, email, hashPassword);
            TokensUserBD tokensUser = new TokensUserBD();
            tokensUser.setRegisteredUsersBD(registeredUsersBD);
            tokensUser.setLogin(login);
            Employee employee = new Employee();
            employee.setRegisteredUsersBD(registeredUsersBD);
            employee.setLogin(login);
            registeredUsersBD.setTokensUserBD(tokensUser);
            registeredUsersBD.setEmployee(employee);

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
        if (bcrypt.checkPassword(password, user.getHash_password())) {
            return user;
        } else {
            return null;
        }
    }

    public RegisteredUsersBD findUserByLogin(String login) {

        RegisteredUsersBD user;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            user = (RegisteredUsersBD) session.createQuery("from RegisteredUsersBD e where e.login = :login")
                    .setParameter("login", login).getSingleResult();


            session.flush();
            tx.commit();
        }
        return user;
    }
}
