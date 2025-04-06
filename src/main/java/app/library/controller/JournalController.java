package app.library.controller;

import app.library.exceptions.JournalRepositoryException;
import app.library.model.Journal;
import app.library.service.JournalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    private final Logger logger = LoggerFactory.getLogger(JournalController.class);

    @GetMapping("/creation/form")
    protected String getJournalForm() {
        return "addJournal";
    }

    @GetMapping("/search/form")
    protected String getJournalFindForm() {
        return "findJournal";
    }

    @PostMapping("/creation")
    protected String createJournal(@RequestParam String name,
                                   @RequestParam String countPages,
                                   @RequestParam String number,
                                   @RequestParam String publicationYear,
                                   Model model) {
        logger.info("Parameters of request. name: {}, countPages: {}, number: {}, pubYear: {}", name, countPages, number, publicationYear);
        try {
            journalService.addJournal(name, countPages, number, publicationYear);
            model.addAttribute("success", "Journal Was successfully Added!!! You can add another one.");
        } catch (JournalRepositoryException e) {
            logger.error("Saving a journal with an error: {} ", e.toString());
            model.addAttribute("error", "Error, while adding a journal. Please try again or contact administrator");
        }
        logger.info("Redirecting to addJournal jsp");
        return "addJournal";
    }

    @GetMapping
    protected String getAllJournals(@RequestParam(required = false) String name, String year, String number, Model model) {
        List<Journal> journals = journalService.getJournals(name, year, number);
        model.addAttribute("journals", journals);
        return "allJournals";
    }


}
