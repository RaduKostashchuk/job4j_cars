package ru.job4j.cars.store;

import ru.job4j.cars.models.Body;

import java.util.List;

public class BodyStore {

    public static List<Body> getAllBodies() {
        return HbmStore.getInstance().tx(session -> session.createQuery("from Body", Body.class).list());
    }

    public static Body getBodyById(int id) {
        return HbmStore.getInstance().tx(session -> session.get(Body.class, id));
    }
}
