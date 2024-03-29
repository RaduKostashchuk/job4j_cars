package ru.job4j.cars.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        if (!uri.endsWith("/add")
                && !uri.endsWith("/upload")
                && !uri.endsWith("/edit")
                && !uri.endsWith("/delete")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (request.getSession().getAttribute("driver") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
