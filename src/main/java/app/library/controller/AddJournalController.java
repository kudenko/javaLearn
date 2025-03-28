package app.library.controller;

import app.library.exceptions.JournalRepositoryException;
import app.library.model.Journal;
import app.library.repository.JournalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/journals/creation")
public class AddJournalController {

    @Autowired
    private JournalRepository journalRepository;

    private final Logger logger = LoggerFactory.getLogger(AddJournalController.class);

    @PostMapping
    protected String createJournal(@RequestParam String name,
                                   @RequestParam String countPages,
                                   @RequestParam String number,
                                   @RequestParam String publicationYear,
                                   Model model) {
        int intCountPages = Integer.parseInt(countPages);
        int intNumber = Integer.parseInt(number);
        int intPublicationYear = Integer.parseInt(publicationYear);
        logger.info("Parameters of request. name: {}, countPages: {}, number: {}, pubYear: {}", name, countPages, number, publicationYear);
        try {
            journalRepository.save(new Journal(name, intCountPages, intNumber, intPublicationYear));
            model.addAttribute("success", "Journal Was successfully Added!!! You can add another one.");
        } catch (JournalRepositoryException e) {
            logger.error("Saving a journal with an error: {} ", e.toString());
            model.addAttribute("error", "Error, while adding a journal. Please try again or contact administrator");
        }
        logger.info("Redirecting to addJournal jsp");
        return "addJournal";
    }
}
