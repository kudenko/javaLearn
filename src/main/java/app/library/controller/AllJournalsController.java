package app.library.controller;

import app.library.model.Journal;
import app.library.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/journals")
public class AllJournalsController {

    @Autowired
    private JournalRepository journalRepository;

    @GetMapping
    protected String getAllJournals(@RequestParam(required = false) String name, String year, String number, Model model) {

        List<Journal> journals = Optional.ofNullable(name)
                .filter(journalName -> !journalName.isEmpty())
                .map(journalName -> {
                    int intYear = Integer.parseInt(year);
                    int intNumber = Integer.parseInt(number);
                    List<Journal> foundJournals = journalRepository.findByNameYearNumber(journalName, intYear, intNumber);
                    return foundJournals;
                })
                .orElseGet(() -> journalRepository.findAll());

        model.addAttribute("journals", journals);
        return "allJournals";
    }
}
