package tmpAnton;

import com.organisation.vacationplanning.database.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TmpEmployeePassDAO {

    public void createUser(String id, String name, String lastname, String email) {

        int idf = Integer.parseInt(id);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            TmpEmployeePassBD tmpEmployeePassBD = new TmpEmployeePassBD(idf, name, lastname, email);
            session.save(tmpEmployeePassBD);

            tx.commit();
        }
    }
}

//http://localhost:8080/VacationPlanning_war_exploded/test?id=4&login=ee&HASH_PASSWORD=ff&E_MAIL=ggg
