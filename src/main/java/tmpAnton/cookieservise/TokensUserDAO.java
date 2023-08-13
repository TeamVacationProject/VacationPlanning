package tmpAnton.cookieservise;

import com.organisation.vacationplanning.database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tmpAnton.signinservise.RegisteredUsersBD;

public class TokensUserDAO {

    public void createToken(String login) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            TokensUserBD tokensUserBD = new TokensUserBD(login);
            session.persist(tokensUserBD);

            session.flush();
            tx.commit();
        }
    }

    public TokensUserBD findToken(String login) {

        TokensUserBD token;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {

                token = (TokensUserBD) session.createQuery("from TokensUserBD e where e.login = :login")
                        .setParameter("login", login).getSingleResult();

            } catch (Exception e) {
                return null;
            }
            session.flush();
            tx.commit();
        }
        return token;
    }

    public TokensUserBD findTokenByID(String login) {

        TokensUserBD tokensUserBD;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {

                tokensUserBD = session.get(TokensUserBD.class, 1);

            } catch (Exception e) {
                return null;
            }
            session.flush();
            tx.commit();
        }
        return tokensUserBD;
    }

    public void deleteToken(String login) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            TokensUserBD tokensUserBD = (TokensUserBD) session.createQuery("from TokensUserBD e where e.login = :login")
                    .setParameter("login", login).getSingleResult();
            session.delete(tokensUserBD);

            session.flush();
            tx.commit();
        }
    }


}
