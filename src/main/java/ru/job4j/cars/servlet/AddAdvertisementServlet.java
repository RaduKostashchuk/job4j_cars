package ru.job4j.cars.servlet;

import ru.job4j.cars.models.*;
import ru.job4j.cars.store.HbmStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAdvertisementServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        int brandId = Integer.parseInt(req.getParameter("brand"));
        int modelId = Integer.parseInt(req.getParameter("model"));
        int bodyId = Integer.parseInt(req.getParameter("body"));
        int engineId = Integer.parseInt(req.getParameter("engine"));
        if (brandId < 1 || modelId < 1 || bodyId < 1 || engineId < 1) {
            resp.sendRedirect(req.getContextPath() + "/html/adv_add_error.html");
            return;
        }
        Brand brand = HbmStore.getInstance().getBrandById(brandId);
        Model model = HbmStore.getInstance().getModelById(modelId);
        Body body = HbmStore.getInstance().getBodyById(bodyId);
        Engine engine = HbmStore.getInstance().getEngineById(engineId);
        Car car = Car.of(brand, model, body, engine);
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        car.addDriver(driver);
        Advertisement advertisement = Advertisement.of(description, driver, car);
        HbmStore.getInstance().save(car);
        HbmStore.getInstance().save(advertisement);
        int advId = advertisement.getId();
        resp.sendRedirect(req.getContextPath() + "/edit.jsp?id=" + advId);
    }
}
