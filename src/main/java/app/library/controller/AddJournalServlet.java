package app.library.controller;

import app.library.config.DatabaseConnectionManager;
import app.library.config.PropertyConfig;
import app.library.exceptions.JournalRepositoryException;
import app.library.model.Journal;
import app.library.storage.JournalRepository;
import app.library.storage.JournalRepositoryCustom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/addJournal")
public class AddJournalServlet extends HttpServlet {
    private JournalRepositoryCustom<Journal> journalRepository;
    private DatabaseConnectionManager connectionManager;

    @Override
    public void init() throws ServletException {
        connectionManager = new DatabaseConnectionManager(new PropertyConfig());
        journalRepository = new JournalRepository(connectionManager);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/addJournal.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int countPages = Integer.parseInt(req.getParameter("countPages"));
        int number = Integer.parseInt(req.getParameter("number"));
        int publicationYear = Integer.parseInt(req.getParameter("publicationYear"));
        try {
            journalRepository.save(new Journal(name, countPages, number, publicationYear));
            req.setAttribute("success", "Journal Was Successfully Added!!! You can add another one.");
        } catch (JournalRepositoryException e) {
            req.setAttribute("error", "Error, while adding a journal. Please try again or contact administrator");
        }
        req.getRequestDispatcher("/html/addJournal.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        connectionManager.close();
        super.destroy();
    }
}
