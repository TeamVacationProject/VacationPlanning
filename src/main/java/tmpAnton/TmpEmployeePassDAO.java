package tmpAnton;

import com.organisation.vacationplanning.database.HibernateUtil;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.organisation.vacationplanning.database.HibernateUtil;
import com.organisation.vacationplanning.database.entities.Employee;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Objects;

public class TmpEmployeePassDAO {

    public void createUser(String id, String E_MAIL, String HASH_PASSWORD, String login) {

//        int idf = Integer.parseInt(id);
////        HashingBcrypt bcrypt = new HashingBcrypt();
////        String pass = bcrypt.getHashPassword("www");
//
//        SessionFactory factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(TmpEmployeePassBD.class)
//                .addAnnotatedClass(TmpCookiesSessionBD.class)
//                .buildSessionFactory();
//
//        Session session = null;
//
//        session = factory.getCurrentSession();
//
//        Transaction tx = Objects.requireNonNull(session).beginTransaction();
//        TmpEmployeePassBD tmpEmployeePassBD1 = new TmpEmployeePassBD(idf, name, lastname, email);
//        session.beginTransaction();
//        session.save(tmpEmployeePassBD1);
//        session.getTransaction().commit();
//        tx.commit();


//        http://localhost:8080/VacationPlanning_war_exploded/test?id=4&login=ee&HASH_PASSWORD=ff&E_MAIL=ggg

        int idf = Integer.parseInt(id);

//        Transaction tx = null;
//        Session session = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

//            TmpEmployeePassBD tmpEmployeePassBD = session.get(TmpEmployeePassBD.class, 1);
//            tmpEmployeePassBD.setEmail("email2");


            TmpEmployeePassBD tmpEmployeePassBD1 = new TmpEmployeePassBD(E_MAIL, HASH_PASSWORD, login);
//            session.beginTransaction();
            session.persist(tmpEmployeePassBD1);
//            session.flush();
//            session.getTransaction().commit();

//            http://localhost:8080/VacationPlanning_war_exploded/test?id=4&E_MAIL=ee&HASH_PASSWORD=ff&LOGIN=ggg
//
            session.flush();
            tx.commit();
        }
    }
}

//https://stackoverflow.com/questions/16275928/hibernate-session-save-does-not-reflect-in-database
//https://www.cyberforum.ru/java-database/thread1876757.html
