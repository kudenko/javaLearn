package app.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = "/items")
public class ItemsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Example data to pass to the JSP
        List<String> items = Arrays.asList("Item 1", "Item 2", "Item 3");
        req.setAttribute("items", items);


        // Forward to the correct index.jsp path
        req.getRequestDispatcher("/html/index.jsp").forward(req, resp);
    }
}

