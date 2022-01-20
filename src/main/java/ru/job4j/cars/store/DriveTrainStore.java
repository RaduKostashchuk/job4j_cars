package ru.job4j.cars.store;

import ru.job4j.cars.models.Drivetrain;

import java.util.List;

public class DriveTrainStore {

    public static List<Drivetrain> getAllDrivetrains() {
        return HbmStore.getInstance().tx(session -> session.createQuery("from Drivetrain ", Drivetrain.class).list());
    }

    public static Drivetrain getDrivetrainById(int id) {
        return HbmStore.getInstance().tx(session -> session.get(Drivetrain.class, id));
    }
}
