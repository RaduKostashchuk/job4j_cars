package ru.job4j.cars.servlet;

import ru.job4j.cars.models.*;
import ru.job4j.cars.store.HbmStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditAdvertisementServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        boolean sold = req.getParameter("sold") != null;
        int advId = Integer.parseInt(req.getParameter("id"));
        int brandId = Integer.parseInt(req.getParameter("brand"));
        int modelId = Integer.parseInt(req.getParameter("model"));
        int bodyId = Integer.parseInt(req.getParameter("body"));
        int engineId = Integer.parseInt(req.getParameter("engine"));
        Advertisement advertisement = HbmStore.getInstance().getAdvertisementById(advId);
        if (brandId != 0) {
            Brand brand = HbmStore.getInstance().getBrandById(brandId);
            advertisement.getCar().setBrand(brand);
        }
        if (modelId != 0) {
            Model model = HbmStore.getInstance().getModelById(modelId);
            advertisement.getCar().setModel(model);
        }
        if (bodyId != 0) {
            Body body = HbmStore.getInstance().getBodyById(bodyId);
            advertisement.getCar().setBody(body);
        }
        if (engineId != 0) {
            Engine engine = HbmStore.getInstance().getEngineById(engineId);
            advertisement.getCar().setEngine(engine);
        }
        if (!description.equals("")) {
            advertisement.setDescription(description);
        }
        advertisement.setId(advId);
        advertisement.setSold(sold);
        HbmStore.getInstance().updateAdvertisement(advertisement);
        resp.sendRedirect("edit.jsp?id=" + req.getParameter("id"));
    }
}
