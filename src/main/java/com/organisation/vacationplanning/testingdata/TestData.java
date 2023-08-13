package com.organisation.vacationplanning.testingdata;

import com.organisation.vacationplanning.database.HibernateUtil;
import com.organisation.vacationplanning.database.entities.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestData {
    public void addTestingEmployee(){
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Employee employee = new Employee();
//            employee.setSurname("Petrov");
//            employee.setFirstName("Petr");
//            employee.setPatronymic("Petrovich");
//
//            Transaction tx = session.beginTransaction();
//            session.persist(employee);
//            tx.commit();
//        }
    }
}
