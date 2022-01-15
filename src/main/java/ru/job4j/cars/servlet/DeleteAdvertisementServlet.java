package ru.job4j.cars.servlet;

import ru.job4j.cars.models.Advertisement;
import ru.job4j.cars.store.HbmStore;

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
        Advertisement advertisement = HbmStore.getInstance().getAdvertisementById(advId);
        int driverIdFromBack = advertisement.getDriver().getId();
        if (driverIdFromFront == driverIdFromBack) {
            HbmStore.getInstance().deleteAdvertisement(advertisement);
            File imageFolder = new File(getServletContext().getInitParameter("imageFolder"));
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
