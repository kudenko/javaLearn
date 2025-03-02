package app.library.controller;

import app.library.model.Journal;
import app.library.storage.JournalRepository;
import app.library.storage.JournalRepositoryCustom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/journals/search/form")
public class FindJournalFormServlet extends HttpServlet {

    private JournalRepositoryCustom<Journal> journalRepository;

    @Override
    public void init() throws ServletException {
        journalRepository = new JournalRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/html/findJournal.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
