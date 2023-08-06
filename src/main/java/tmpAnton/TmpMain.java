package tmpAnton;

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

import static com.organisation.vacationplanning.database.HibernateUtil.getSessionFactory;

public class TmpMain {
    public static void main(String[] args) {

        HashingBcrypt bcrypt = new HashingBcrypt();
        String pass = bcrypt.getHashPassword("www");

//        SessionFactory factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(TmpEmployeePassBD.class)
//                .addAnnotatedClass(TmpCookiesSessionBD.class)
//                .buildSessionFactory();

//        Session session = null;

//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Transaction tx = session.beginTransaction();
//
//            TmpEmployeePassBD tmpEmployeePassBD = new TmpEmployeePassBD(4, "ttt", pass, "email4");
//            session.save(tmpEmployeePassBD);
//
//            tx.commit();
//        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Employee employee = new Employee(2L, "ff", "gg", "hh");
            session.merge(employee);

            tx.commit();
        }

        try {

            //CREATE
//            session = factory.getCurrentSession();
//            TmpEmployeePassBD tmpEmployeePassBD = new TmpEmployeePassBD(3, "rrr", pass, "email");
//            session.beginTransaction();
//            session.save(tmpEmployeePassBD);
//            session.getTransaction().commit();

            //READ and Check Password
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            TmpEmployeePassBD tmpEmployeePassBD = session.get(TmpEmployeePassBD.class, 1);
//            session.getTransaction().commit();
//            System.out.println("Start");
//            System.out.println(tmpEmployeePassBD);
//            String passCheck = bcrypt.getHashPassword("www");
//            boolean d = bcrypt.checkPassword("www", tmpEmployeePassBD.getHashPassword());
//            System.out.println("Start check");
//            System.out.println(d);


//            session = factory.getCurrentSession();
//            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//            CriteriaQuery<TmpEmployeePassBD> criteriaQuery = criteriaBuilder.createQuery(TmpEmployeePassBD.class);
//            Root<TmpEmployeePassBD> root = criteriaQuery.from(TmpEmployeePassBD.class);
//            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("E_MAIL"), "email1"));
//            Query<TmpEmployeePassBD> query = session.createQuery(criteriaQuery);
//            TmpEmployeePassBD employee = query.getSingleResult();
//            System.out.println(employee); //ToDo почему ищет именно по ИД, как по другому столбцу?


            //UPDATE
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            TmpEmployeePassBD tmpEmployeePassBD = session.get(TmpEmployeePassBD.class, 1);
//            tmpEmployeePassBD.setEmail("email1");
//            session.getTransaction().commit();
//            System.out.println("Start");
//            System.out.println(tmpEmployeePassBD);

            //DELETE
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            TmpEmployeePassBD tmpEmployeePassBD = session.get(TmpEmployeePassBD.class, 3);
//            session.delete(tmpEmployeePassBD);
//            session.getTransaction().commit();


        } catch (HibernateException e) {
            throw new RuntimeException(e);
        } finally {
//            factory.close();
//            if (session != null) {
//                session.close();
//            }
        }


    }
}
