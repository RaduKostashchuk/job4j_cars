package ru.job4j.cars.servlet;

import ru.job4j.cars.models.*;
import ru.job4j.cars.store.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAdvertisementServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        int brandId = Integer.parseInt(req.getParameter("brand"));
        int modelId = Integer.parseInt(req.getParameter("model"));
        int bodyId = Integer.parseInt(req.getParameter("body"));
        int engineId = Integer.parseInt(req.getParameter("engine"));
        int drivetrainId = Integer.parseInt(req.getParameter("drivetrain"));
        int year = Integer.parseInt(req.getParameter("year"));
        int mileage = Integer.parseInt(req.getParameter("mileage"));
        Brand brand = BrandStore.getBrandById(brandId);
        Model model = ModelStore.getModelById(modelId);
        Body body = BodyStore.getBodyById(bodyId);
        Engine engine = EngineStore.getEngineById(engineId);
        Drivetrain drivetrain = DriveTrainStore.getDrivetrainById(drivetrainId);
        Car car = Car.of(brand, model, body, engine, year, mileage, drivetrain);
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        car.addDriver(driver);
        Advertisement advertisement = Advertisement.of(description, driver, car, price);
        CarStore.save(car);
        AdvertisementStore.save(advertisement);
        int advId = advertisement.getId();
        resp.sendRedirect(req.getContextPath() + "/edit.jsp?id=" + advId);
    }
}
