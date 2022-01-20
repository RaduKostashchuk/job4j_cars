package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.*;
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

    public Session getSession() {
        return sf
                .withOptions()
                .jdbcTimeZone(TimeZone.getTimeZone("UTC"))
                .openSession();
    }

    public <T> T tx(Function<Session, T> command) {
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
