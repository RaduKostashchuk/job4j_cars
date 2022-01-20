package ru.job4j.cars.store;

import ru.job4j.cars.models.Model;

import java.util.List;

public class ModelStore {

    public static List<Model> getBrandModels(int brandId) {
        return HbmStore.getInstance().tx(session -> session.createQuery("from Model m"
                        + " join fetch m.brand where m.brand.id = :id", Model.class)
                .setParameter("id", brandId)
                .list());
    }

    public static Model getModelById(int id) {
        return HbmStore.getInstance().tx(session -> session.get(Model.class, id));
    }
}
