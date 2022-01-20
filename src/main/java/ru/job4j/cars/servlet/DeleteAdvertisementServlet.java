package ru.job4j.cars.servlet;

import ru.job4j.cars.config.Config;
import ru.job4j.cars.models.Advertisement;
import ru.job4j.cars.store.AdvertisementStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteAdvertisementServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int advId = Integer.parseInt(req.getParameter("advId"));
        int driverIdFromFront = Integer.parseInt(req.getParameter("driverId"));
        Advertisement advertisement = AdvertisementStore.getAdvertisementById(advId);
        int driverIdFromBack = advertisement.getDriver().getId();
        if (driverIdFromFront == driverIdFromBack) {
            AdvertisementStore.deleteAdvertisement(advertisement);
            Config config = new Config();
            File imageFolder = new File(config.get("cars.image_folder"));
            for (File file : imageFolder.listFiles()) {
                if (file.getName().matches(advId + "\\..+")) {
                    file.delete();
                }
            }
            resp.sendRedirect("html/adv_delete_success.html");
        } else {
            resp.sendRedirect("html/adv_delete_failure.html");
        }
    }
}
