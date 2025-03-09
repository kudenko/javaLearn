package app.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(urlPatterns = "/authors/search/form")
public class FindAuthorFormServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(FindBookServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Redirecting to find author page");
        req.getRequestDispatcher("/html/findAuthor.jsp").forward(req, resp);
    }
}
