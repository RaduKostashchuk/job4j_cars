package ru.job4j.cars.store;

import ru.job4j.cars.models.Advertisement;
import java.util.*;

public class AdvertisementStore {

    public static void save(Advertisement advertisement) {
        HbmStore.getInstance().tx(session -> session.save(advertisement));
    }

    public static List<Advertisement> getAllAdvertisements() {
        return HbmStore.getInstance().tx(session -> session
                .createQuery("select distinct a from Advertisement a "
                        + "join fetch a.driver "
                        + "join fetch a.car c "
                        + "join fetch c.body "
                        + "join fetch c.drivetrain "
                        + "join fetch c.brand "
                        + "join fetch c.model "
                        + "join fetch c.engine ", Advertisement.class)
                .list());
    }

    public static Advertisement getAdvertisementById(int id) {
        return HbmStore.getInstance().tx(session -> session
                .createQuery("select distinct a from Advertisement a "
                        + " join fetch a.driver"
                        + " join fetch a.car c"
                        + "  join fetch c.drivetrain"
                        + " join fetch c.body"
                        + " join fetch c.brand"
                        + " join fetch c.model"
                        + " join fetch c.engine"
                        + " where a.id = :id", Advertisement.class)
                .setParameter("id", id)
                .uniqueResult());
    }

    public static void updateAdvertisement(Advertisement advertisement) {
        HbmStore.getInstance().tx(session -> {
            session.update(advertisement);
            return true;
        });
    }

    public static void deleteAdvertisement(Advertisement advertisement) {
        HbmStore.getInstance().tx(session -> {
            session.remove(advertisement);
            return true;
        });
    }
}
