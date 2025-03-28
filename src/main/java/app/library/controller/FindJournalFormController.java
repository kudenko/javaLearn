package app.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/journals/search/form")
public class FindJournalFormController {

    @GetMapping
    protected String getJournalFindForm() {
        return "findJournal";
    }
}
