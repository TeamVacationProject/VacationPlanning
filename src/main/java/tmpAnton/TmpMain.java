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

import java.util.List;

import static com.organisation.vacationplanning.database.HibernateUtil.getSessionFactory;

public class TmpMain {
    public static void main(String[] args) {

        HashingBcrypt bcrypt = new HashingBcrypt();
        String pass = bcrypt.getHashPassword("www");


        //CREATE
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            TmpEmployeePassBD tmpEmployeePassBD = new TmpEmployeePassBD("ggg3", "pass2", "email9");
            session.persist(tmpEmployeePassBD);

//            session.flush();
            tx.commit();
        }

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
