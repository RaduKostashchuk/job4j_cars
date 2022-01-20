package ru.job4j.cars.store;

import ru.job4j.cars.models.Brand;

import java.util.List;

public class BrandStore {

    public static List<Brand> getAllBrands() {
        return HbmStore.getInstance().tx(session -> session.createQuery("from Brand", Brand.class).list());
    }

    public static Brand getBrandById(int id) {
        return HbmStore.getInstance().tx(session -> session.get(Brand.class, id));
    }
}
