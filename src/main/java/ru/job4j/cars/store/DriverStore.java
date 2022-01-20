package ru.job4j.cars.store;

import org.hibernate.Session;
import ru.job4j.cars.models.Driver;

import javax.persistence.NoResultException;

public class DriverStore {

    public static void save(Driver driver) {
        HbmStore.getInstance().tx(session -> session.save(driver));
    }

    public static Driver findDriverByEmail(String email) {
        Driver driver = null;
        Session session = HbmStore.getInstance().getSession();
        try {
            session.beginTransaction();
            driver = session.createQuery("from Driver where email = :email", Driver.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return driver;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
        return driver;
    }

}
