package app.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books/search/form")
public class FindBookFormController {

    @GetMapping
    protected String getBookFindForm() {
        return "findBook";
    }
}
