package app.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authors/search/form")
public class FindAuthorFormController {

    @GetMapping
    protected String getFindAuthorForm() {
        return "findAuthor";
    }
}
