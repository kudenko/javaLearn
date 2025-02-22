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

@WebServlet(urlPatterns = "/findJournal")
public class FindJournalServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String name = req.getParameter("name");
        int year = Integer.parseInt(req.getParameter("year"));
        int number = Integer.parseInt(req.getParameter("number"));

        List<Journal> journals = journalRepository.findByNameYearNumber(name, year, number);

        req.setAttribute("journals", journals);
        req.getRequestDispatcher("/html/allJournals.jsp").forward(req, res);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
