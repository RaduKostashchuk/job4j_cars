package ru.job4j.cars.servlet;

import ru.job4j.cars.models.*;
import ru.job4j.cars.store.*;

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
        int drivetrainId = Integer.parseInt(req.getParameter("drivetrain"));
        String year = req.getParameter("year");
        String mileage = req.getParameter("mileage");
        String price = req.getParameter("price");
        Advertisement advertisement = AdvertisementStore.getAdvertisementById(advId);
        if (!year.isEmpty()) {
            advertisement.getCar().setYear(Integer.parseInt(year));
        }
        if (!mileage.isEmpty()) {
            advertisement.getCar().setMileage(Integer.parseInt(mileage));
        }
        if (!price.isEmpty()) {
            advertisement.setPrice(Integer.parseInt(price));
        }
        if (brandId != 0) {
            Brand brand = BrandStore.getBrandById(brandId);
            advertisement.getCar().setBrand(brand);
        }
        if (modelId != 0) {
            Model model = ModelStore.getModelById(modelId);
            advertisement.getCar().setModel(model);
        }
        if (bodyId != 0) {
            Body body = BodyStore.getBodyById(bodyId);
            advertisement.getCar().setBody(body);
        }
        if (engineId != 0) {
            Engine engine = EngineStore.getEngineById(engineId);
            advertisement.getCar().setEngine(engine);
        }
        if (drivetrainId != 0) {
            Drivetrain drivetrain = DriveTrainStore.getDrivetrainById(drivetrainId);
            advertisement.getCar().setDrivetrain(drivetrain);
        }
        if (!description.equals("")) {
            advertisement.setDescription(description);
        }
        advertisement.setId(advId);
        advertisement.setSold(sold);
        AdvertisementStore.updateAdvertisement(advertisement);
        resp.sendRedirect("edit.jsp?id=" + req.getParameter("id"));
    }
}
