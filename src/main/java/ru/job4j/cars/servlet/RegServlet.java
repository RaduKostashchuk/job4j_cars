package ru.job4j.cars.servlet;

import ru.job4j.cars.models.Driver;
import ru.job4j.cars.store.DriverStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        Driver driver = DriverStore.findDriverByEmail(email);
        if (driver == null) {
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            String password = req.getParameter("password");
            driver = Driver.of(name, email, phone, password);
            DriverStore.save(driver);
            req.setAttribute("regMessage", "Регистрация прошла успешно");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Пользователь с таким email уже существует");
            req.getRequestDispatcher("/reg.jsp").forward(req, resp);
        }

    }
}
