package ru.job4j.cars.servlet;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.models.Advertisement;
import ru.job4j.cars.models.Car;
import ru.job4j.cars.models.Driver;
import ru.job4j.cars.store.AdvertisementStore;
import ru.job4j.cars.store.Filter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class GetAllAdvertisementsServlet extends HttpServlet {
    private final Map<Filter.Type, BiFunction<String, List<Advertisement>, List<Advertisement>>> dispatcher = new HashMap<>();

    @Override
    public void init() throws ServletException {
        dispatcher.put(Filter.Type.BRAND, Filter::getAdvertisementByBrandName);
        dispatcher.put(Filter.Type.LAST_DAY, Filter::getLastDayAdvertisements);
        dispatcher.put(Filter.Type.PHOTO, Filter::getAdvertisementWithPhoto);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(getStrategy())
                .create();
        String withPhoto = req.getParameter("photo");
        String lastDay = req.getParameter("lastDay");
        String brand = req.getParameter("brand");
        Map<Filter.Type, String> filters = Filter.of(brand, withPhoto, lastDay);
        List<Advertisement> advertisements = AdvertisementStore.getAllAdvertisements();
        for (Filter.Type fType : filters.keySet()) {
            advertisements = dispatcher.get(fType).apply(filters.get(fType), advertisements);
        }
        String json = gson.toJson(advertisements);
        OutputStream out = resp.getOutputStream();
        out.write(json.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }

    private ExclusionStrategy getStrategy() {
        return new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes field) {
                if (field.getDeclaringClass() == Car.class && field.getName().equals("drivers")) {
                    return true;
                }
                if (field.getDeclaringClass() == Driver.class && !field.getName().equals("id")) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };
    }

}
