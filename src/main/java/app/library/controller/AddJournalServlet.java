package app.library.controller;

import app.library.exceptions.JournalRepositoryException;
import app.library.model.Journal;
import app.library.repository.JournalRepository;
import app.library.repository.JournalRepositoryCustom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;

@Configurable
@WebServlet(urlPatterns = "/journals/creation")
public class AddJournalServlet extends HttpServlet {
    private JournalRepository journalRepository;

    private final Logger logger = LoggerFactory.getLogger(AddJournalServlet.class);

    @Override
    public void init() throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int countPages = Integer.parseInt(req.getParameter("countPages"));
        int number = Integer.parseInt(req.getParameter("number"));
        int publicationYear = Integer.parseInt(req.getParameter("publicationYear"));
        logger.info("Parameters of request. name: {}, countPages: {}, number: {}, pubYear: {}", name, countPages, number, publicationYear);
        try {
            journalRepository.save(new Journal(name, countPages, number, publicationYear));
            req.setAttribute("success", "Journal Was successfully Added!!! You can add another one.");
        } catch (JournalRepositoryException e) {
            logger.error("Saving a journal with an error: {} ", e.toString());
            req.setAttribute("error", "Error, while adding a journal. Please try again or contact administrator");
        }
        logger.info("Redirecting to addJournal jsp");
        req.getRequestDispatcher("/html/addJournal.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        logger.info("Destroy started.");
        super.destroy();
        logger.info("Destroy completed.");
    }

    @Autowired
    public void setJournalRepository(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }
}
