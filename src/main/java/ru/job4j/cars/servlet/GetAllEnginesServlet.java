package ru.job4j.cars.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.cars.models.Engine;
import ru.job4j.cars.store.HbmStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GetAllEnginesServlet extends HttpServlet {
    private static final Gson GSON = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Engine> engines = HbmStore.getInstance().getAllEngines();
        String json = GSON.toJson(engines);
        OutputStream out = resp.getOutputStream();
        out.write(json.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }
}
