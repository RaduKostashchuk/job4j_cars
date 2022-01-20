package ru.job4j.cars.servlet;

import ru.job4j.cars.config.Config;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Config config = new Config();
        String imageFolder = config.get("cars.image_folder");
        File photo = null;
        String name  = req.getParameter("id");
        if (name != null) {
            for (File file : Objects.requireNonNull(new File(imageFolder).listFiles())) {
                if (name.equals(file.getName().split("\\.")[0])) {
                    photo = file;
                    break;
                }
            }
        }
        if (photo != null) {
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + photo.getName() + "\"");
            try (FileInputStream input = new FileInputStream(photo)) {
                resp.getOutputStream().write(input.readAllBytes());
            }
        }
    }
}
