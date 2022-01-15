package ru.job4j.cars.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        File imageFolder = new File(getServletContext().getInitParameter("imageFolder"));
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext context = this.getServletConfig().getServletContext();
        File repository = (File) context.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            if (!imageFolder.exists()) {
                imageFolder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    File file = new File(imageFolder + File.separator + req.getParameter("id") + ".jpg");
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("edit.jsp?id=" + req.getParameter("id"));
    }
}
