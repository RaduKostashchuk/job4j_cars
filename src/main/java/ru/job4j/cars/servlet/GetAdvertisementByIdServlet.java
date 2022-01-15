package ru.job4j.cars.servlet;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.models.Advertisement;
import ru.job4j.cars.models.Car;
import ru.job4j.cars.models.Driver;
import ru.job4j.cars.store.HbmStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetAdvertisementByIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(getStrategy())
                .create();
        int id = Integer.parseInt(req.getParameter("id"));
        Advertisement advertisement = HbmStore.getInstance().getAdvertisementById(id);
        String json = gson.toJson(advertisement);
        OutputStream out = resp.getOutputStream();
        out.write(json.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }

    private ExclusionStrategy getStrategy() {
        return new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes field) {
                if (field.getDeclaringClass() == Driver.class && !field.getName().equals("id")) {
                    return true;
                }
                if (field.getDeclaringClass() == Car.class && field.getName().equals("drivers")) {
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
