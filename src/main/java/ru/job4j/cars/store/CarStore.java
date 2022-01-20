package ru.job4j.cars.store;

import ru.job4j.cars.models.Car;

public class CarStore {

    public static void save(Car car) {
        HbmStore.getInstance().tx(session -> session.save(car));
    }
}
