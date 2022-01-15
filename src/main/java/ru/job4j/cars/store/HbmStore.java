package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.models.*;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.TimeZone;
import java.util.function.Function;

public class HbmStore implements AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
    private final SessionFactory sf = new MetadataSources(registry)
                            .buildMetadata()
                            .buildSessionFactory();

    private HbmStore() { }

    private static class Holder {
        private static final HbmStore INSTANCE = new HbmStore();
    }

    public static HbmStore getInstance() {
        return Holder.INSTANCE;
    }

    public void save(Driver driver) {
        tx(session -> session.save(driver));
    }

    public void save(Advertisement advertisement) {
        tx(session -> session.save(advertisement));
    }

    public void save(Car car) {
        tx(session -> session.save(car));
    }

    public Driver findDriverByEmail(String email) {
        Driver driver = null;
        Session session = sf.openSession();
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

    public List<Advertisement> getAllAdvertisements() {
        return tx(session -> session
                .createQuery("select distinct a from Advertisement a "
                        + "join fetch a.driver "
                        + "join fetch a.car c "
                        + "join fetch c.body "
                        + "join fetch c.brand "
                        + "join fetch c.model "
                        + "join fetch c.engine ", Advertisement.class)
                .list());
    }

    public List<Body> getAllBodies() {
        return tx(session -> session.createQuery("from Body", Body.class).list());
    }

    public List<Engine> getAllEngines() {
        return tx(session -> session.createQuery("from Engine", Engine.class).list());
    }

    public List<Brand> getAllBrands() {
        return tx(session -> session.createQuery("from Brand", Brand.class).list());
    }

    public List<Model> getBrandModels(int brandId) {
        return tx(session -> session.createQuery("from Model m"
                        + " join fetch m.brand where m.brand.id = :id", Model.class)
                .setParameter("id", brandId)
                .list());
    }

    public Advertisement getAdvertisementById(int id) {
        return tx(session -> session
                .createQuery("select distinct a from Advertisement a "
                        + "join fetch a.driver "
                        + "join fetch a.car c "
                        + "join fetch c.body "
                        + "join fetch c.brand "
                        + "join fetch c.model "
                        + "join fetch c.engine where a.id = :id", Advertisement.class)
                .setParameter("id", id)
                .uniqueResult());
    }

    public void updateAdvertisement(Advertisement advertisement) {
        tx(session -> {
            session.update(advertisement);
            return true;
        });
    }

    public void deleteAdvertisement(Advertisement advertisement) {
        tx(session -> {
            session.remove(advertisement);
            return true;
        });
    }

    public Brand getBrandById(int id) {
        return tx(session -> session.get(Brand.class, id));
    }

    public Model getModelById(int id) {
        return tx(session -> session.get(Model.class, id));
    }

    public Body getBodyById(int id) {
        return tx(session -> session.get(Body.class, id));
    }

    public Engine getEngineById(int id) {
        return tx(session -> session.get(Engine.class, id));
    }

    private <T> T tx(Function<Session, T> command) {
        T result = null;
        Session session = sf
                .withOptions()
                .jdbcTimeZone(TimeZone.getTimeZone("UTC"))
                .openSession();
        try (session) {
            session.beginTransaction();
            result = command.apply(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() {
        sf.close();
    }
}
