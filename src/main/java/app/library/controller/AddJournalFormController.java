package app.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/journals/creation/form")
public class AddJournalFormController {

    @GetMapping
    protected String getJournalForm() {
        return "addJournal";
    }
}
