package app.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/authors/creation/form")
public class AddAuthorFormController {

    @GetMapping
    protected String getAuthorsCreationForm() {
        return "addAuthor";
    }
}
