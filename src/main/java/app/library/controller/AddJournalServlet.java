package app.library.controller;

import app.library.exceptions.JournalRepositoryException;
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

@WebServlet(urlPatterns = "/journals/creation")
public class AddJournalServlet extends HttpServlet {
    private JournalRepositoryCustom<Journal> journalRepository;

    private final Logger logger = LoggerFactory.getLogger(AddJournalServlet.class);

    @Override
    public void init() throws ServletException {
        logger.info("Journal repository initialization");
        journalRepository = new JournalRepository();
        logger.info("Journal repository initialization successful");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int countPages = Integer.parseInt(req.getParameter("countPages"));
        int number = Integer.parseInt(req.getParameter("number"));
        int publicationYear = Integer.parseInt(req.getParameter("publicationYear"));
        logger.info("Parameters of request. name: {}, countPages: {}, number: {}, pubYear: {}", name, countPages, number, publicationYear);
        try {
            logger.info("Saving a journal");
            journalRepository.save(new Journal(name, countPages, number, publicationYear));
            req.setAttribute("success", "Journal Was successfully Added!!! You can add another one.");
            logger.info("Saving successful");
        } catch (JournalRepositoryException e) {
            logger.error("Saving a journal with an error: {} ", e.toString());
            req.setAttribute("error", "Error, while adding a journal. Please try again or contact administrator");
        }
        logger.info("Redirecting to addJournal jsp");
        req.getRequestDispatcher("/html/addJournal.jsp").forward(req, resp);
        logger.info("Redirecting to addJournal jsp successful");
    }

    @Override
    public void destroy() {
        logger.info("Destroy started.");
        super.destroy();
        logger.info("Destroy completed.");
    }
}
