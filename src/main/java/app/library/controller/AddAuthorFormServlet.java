package app.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(urlPatterns = "/authors/creation/form")
public class AddAuthorFormServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(AddAuthorFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request for author creation started.");
        req.getRequestDispatcher("/html/addAuthor.jsp").forward(req, resp);
        logger.info("Request for author creation completed.");
    }
}
