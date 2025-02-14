package app.library.controller;

import app.library.config.DatabaseConnectionManager;
import app.library.config.PropertyConfig;
import app.library.model.Book;
import app.library.model.Journal;
import app.library.storage.BookRepository;
import app.library.storage.BookRepositoryCustom;
import app.library.storage.JournalRepository;
import app.library.storage.JournalRepositoryCustom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/allJournals")
public class AllJournalsServlet extends HttpServlet {

    JournalRepositoryCustom<Journal> journalRepository;
    DatabaseConnectionManager connectionManager;

    @Override
    public void init() throws ServletException {
        connectionManager = new DatabaseConnectionManager(new PropertyConfig());
        journalRepository = new JournalRepository(connectionManager);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Journal> journals = journalRepository.findAll();
        req.setAttribute("journals", journals);
        req.getRequestDispatcher("/html/allJournals.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        connectionManager.close();
        super.destroy();
    }
}
