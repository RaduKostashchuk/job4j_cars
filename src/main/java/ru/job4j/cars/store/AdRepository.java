package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.models.Advertisement;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AdRepository {
    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder()
            .configure()
            .build();
    private static final SessionFactory SF = new MetadataSources(REGISTRY)
            .buildMetadata()
            .buildSessionFactory();

    public static List<Advertisement> getLastDayAdvertisements() {
            return tx(session ->  {
                Date dateMinusOneDay = Timestamp.valueOf(LocalDateTime.now().minusDays(1));
                return session.createQuery("select distinct a from Advertisement a "
                                + " join fetch a.car c"
                                + " join fetch c.engine"
                                + " join fetch c.body"
                                + " join fetch c.brand"
                                + " join fetch c.model"
                                + " join fetch c.drivers"
                                + " where a.created >= :date", Advertisement.class)
                        .setParameter("date", dateMinusOneDay).list();
            });
    }

    public static List<Advertisement> getAdvertisementWithPhoto() {
        return tx(session -> {
            File imageFolder = new File("c:/images/carsale");
            List<Integer> ids = Arrays.stream(Objects.requireNonNull(imageFolder.listFiles()))
                    .map(f -> Integer.parseInt(f.getName().split("\\.")[0]))
                    .collect(Collectors.toList());
            return session.createQuery("select distinct a from Advertisement a "
                    + " join fetch a.car c"
                    + " join fetch c.engine"
                    + " join fetch c.body"
                    + " join fetch c.brand"
                    + " join fetch c.model"
                    + " join fetch c.drivers"
                    + " where a.id in :ids", Advertisement.class)
                    .setParameter("ids", ids)
                    .getResultList();
            });
    }

    public static List<Advertisement> getAdvertisementByBrandName(String name) {
        return tx(session -> session.createQuery("select distinct a from Advertisement a "
                            + " join fetch a.car c"
                            + " join fetch c.engine"
                            + " join fetch c.body"
                            + " join fetch c.brand b"
                            + " join fetch c.model"
                            + " join fetch c.drivers"
                            + " where b.name = :name", Advertisement.class)
                    .setParameter("name", name)
                    .getResultList());
    }

    private static <T> T tx(Function<Session, T> command) {
        T result = null;
        Session session = SF
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

    public static void main(String[] args) {
        for (Advertisement advertisement : getLastDayAdvertisements()) {
            System.out.println(advertisement);
        }
        for (Advertisement advertisement : getAdvertisementWithPhoto()) {
            System.out.println(advertisement);
        }
        for (Advertisement advertisement : getAdvertisementByBrandName("Toyota")) {
            System.out.println(advertisement);
        }

        StandardServiceRegistryBuilder.destroy(REGISTRY);
    }
}
