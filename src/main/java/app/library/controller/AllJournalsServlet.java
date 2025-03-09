package app.library.controller;

import app.library.model.Journal;
import app.library.storage.JournalRepository;
import app.library.storage.JournalRepositoryCustom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/journals")
public class AllJournalsServlet extends HttpServlet {

    private JournalRepositoryCustom<Journal> journalRepository;

    private final Logger logger = LoggerFactory.getLogger(AllJournalsServlet.class);

    @Override
    public void init() throws ServletException {
        logger.info("Journal repository initialization");
        journalRepository = new JournalRepository();
        logger.info("Journal repository initialization successful");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Request for all journals from servlet");

        List<Journal> journals = Optional.ofNullable(req.getParameter("name"))
                .filter(journalName -> !journalName.isEmpty())
                .map(journalName -> {
                    int year = Integer.parseInt(req.getParameter("year"));
                    int number = Integer.parseInt(req.getParameter("number"));
                    List<Journal> foundJournals = journalRepository.findByNameYearNumber(journalName, year, number);
                    return foundJournals;
                })
                .orElseGet(() -> journalRepository.findAll());

        req.setAttribute("journals", journals);
        logger.info("Redirecting to all journals page");
        req.getRequestDispatcher("/html/allJournals.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        logger.info("Destroy started.");
        super.destroy();
        logger.info("Destroy completed.");
    }
}
