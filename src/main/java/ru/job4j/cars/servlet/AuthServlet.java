package ru.job4j.cars.servlet;

import ru.job4j.cars.models.Driver;
import ru.job4j.cars.store.DriverStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Driver driver = DriverStore.findDriverByEmail(email);
        if (driver != null && driver.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("driver", driver);
            resp.sendRedirect(req.getContextPath());
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
