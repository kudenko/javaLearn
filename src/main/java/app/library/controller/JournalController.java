package app.library.controller;

import app.library.model.Journal;
import app.library.service.JournalService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/journals")
public class JournalController {
    private JournalService journalService;

    private final Logger logger = LoggerFactory.getLogger(JournalController.class);

    @Autowired

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("/form")
    protected String getJournalForm(Model model, HttpServletRequest request) {
        model.addAttribute("journal", new Journal());
        request.setAttribute("viewName", "addJournal");
        return "addJournal";
    }

    @GetMapping("/search")
    protected String getJournalFindForm(HttpServletRequest request) {
        request.setAttribute("viewName", "findJournal");
        return "findJournal";
    }

    @PostMapping
    protected String createJournal(@ModelAttribute("journal") @Valid Journal journal, BindingResult result,
                                   Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "addJournal";
        }

        request.setAttribute("viewName", "addJournal");

        logger.info("Parameters of request. name: {}, countPages: {}, number: {}, pubYear: {}", journal.getName(), journal.getCountPages(), journal.getNumber(), journal.getPublicationYear());
        journalService.addJournal(journal);
        model.addAttribute("success", "Journal Was successfully Added!!! You can add another one.");
        logger.info("Redirecting to addJournal jsp");
        return "addJournal";
    }

    @GetMapping
    protected String getAllJournals(@RequestParam(required = false) String name, String year, String number, Model model, HttpServletRequest request) {
        List<Journal> journals = journalService.getJournals(name, year, number);
        model.addAttribute("journals", journals);
        request.setAttribute("viewName", "allJournals");
        return "allJournals";
    }
}
