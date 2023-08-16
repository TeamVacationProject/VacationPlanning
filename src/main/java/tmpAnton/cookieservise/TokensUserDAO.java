package tmpAnton.cookieservise;

import com.organisation.vacationplanning.database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TokensUserDAO {

    public void createToken(String login) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            TokensUserBD tokensUserBD = findToken(login);
            tokensUserBD.createTokenUUID();
            tokensUserBD.createExpireTime();
            tokensUserBD.setLogin(login);

            session.update(tokensUserBD);

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

    public TokensUserBD findTokenByID(int registered_user_id) {

        TokensUserBD tokensUserBD;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {

                tokensUserBD = session.get(TokensUserBD.class, registered_user_id);

            } catch (Exception e) {
                return null;
            }
            session.flush();
            tx.commit();
        }
        return tokensUserBD;
    }

    public void updateTokenUser(String login) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            TokensUserBD tokensUserBD = (TokensUserBD) session.createQuery("from TokensUserBD e where e.login = :login")
                    .setParameter("login", login).getSingleResult();

            tokensUserBD.createTokenUUID();
            tokensUserBD.createExpireTime();
            session.update(tokensUserBD);
            session.flush();
            tx.commit();
        }
    }


}
