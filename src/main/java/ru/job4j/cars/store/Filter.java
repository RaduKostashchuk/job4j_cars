package ru.job4j.cars.store;

import ru.job4j.cars.config.Config;
import ru.job4j.cars.models.Advertisement;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class Filter {

    public enum Type {
        BRAND,
        LAST_DAY,
        PHOTO,
        EMPTY
    }

    public static Map<Filter.Type, String> of(String brand, String withPhoto, String lastDay) {
        Map<Filter.Type, String> result = new HashMap<>();
        result.put(brand.equals("Все марки") ? Type.EMPTY : Type.BRAND, brand);
        result.put(withPhoto == null ? Type.EMPTY : Type.PHOTO, withPhoto);
        result.put(lastDay == null ? Type.EMPTY : Type.LAST_DAY, lastDay);
        result.remove(Type.EMPTY);
        return result;
    }

    public static List<Advertisement> getAdvertisementByBrandName(String brand, List<Advertisement> advertisements) {
        List<Advertisement> result = new ArrayList<>();
        for (Advertisement advertisement : advertisements) {
            String name = advertisement.getCar().getBrand().getName();
            if (name.equals(brand)) {
                result.add(advertisement);
            }
        }
        return result;
    }

    public static List<Advertisement> getLastDayAdvertisements(String dummy, List<Advertisement> advertisements) {
        List<Advertisement> result = new ArrayList<>();
        Date dateMinusOneDay = Timestamp.valueOf(LocalDateTime.now().minusDays(1));
        for (Advertisement advertisement : advertisements) {
             Date created = advertisement.getCreated();
            if (created.after(dateMinusOneDay)) {
                result.add(advertisement);
            }
        }
        return result;
    }

    public static List<Advertisement> getAdvertisementWithPhoto(String dummy, List<Advertisement> advertisements) {
        List<Advertisement> result = new ArrayList<>();
        Config config = new Config();
        File imageFolder = new File(config.get("cars.image_folder"));
        List<Integer> ids = Arrays.stream(Objects.requireNonNull(imageFolder.listFiles()))
                .map(f -> Integer.parseInt(f.getName().split("\\.")[0])).toList();
        for (Advertisement advertisement : advertisements) {
            if (ids.contains(advertisement.getId())) {
                result.add(advertisement);
            }
        }
        return result;
    }
}
