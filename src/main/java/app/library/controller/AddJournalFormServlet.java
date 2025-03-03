package app.library.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(urlPatterns = "/journals/creation/form")
public class AddJournalFormServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(AddJournalFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("GET request to addJournal form");
        req.getRequestDispatcher("/html/addJournal.jsp").forward(req, resp);
        logger.info("GET request to addJournal form successful.");
    }
}
