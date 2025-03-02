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
import java.util.List;

@WebServlet(urlPatterns = "/allJournals")
public class AllJournalsServlet extends HttpServlet {

    private JournalRepositoryCustom<Journal> journalRepository;

    @Override
    public void init() throws ServletException {
        journalRepository = new JournalRepository();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Journal> journals = journalRepository.findAll();
        req.setAttribute("journals", journals);
        req.getRequestDispatcher("/html/allJournals.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
