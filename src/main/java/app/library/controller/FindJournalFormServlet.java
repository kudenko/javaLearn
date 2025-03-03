package app.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(urlPatterns = "/journals/search/form")
public class FindJournalFormServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(FindJournalFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Redirecting to find journals page");
        req.getRequestDispatcher("/html/findJournal.jsp").forward(req, resp);
        logger.info("Redirecting to find journals page successful");
    }
}
