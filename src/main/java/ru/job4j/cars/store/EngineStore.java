package ru.job4j.cars.store;

import ru.job4j.cars.models.Engine;

import java.util.List;

public class EngineStore {

    public static List<Engine> getAllEngines() {
        return HbmStore.getInstance().tx(session -> session.createQuery("from Engine", Engine.class).list());
    }

    public static Engine getEngineById(int id) {
        return HbmStore.getInstance().tx(session -> session.get(Engine.class, id));
    }
}
