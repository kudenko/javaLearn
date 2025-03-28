package app.library.controller;

import app.library.model.Author;
import app.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AllAuthorsController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    protected String getAuthors(@RequestParam(required = false) String email, Model model) {
        List<Author> authors = Optional.ofNullable(email)
                .map(authorRepository::findByEmail)
                .orElseGet(authorRepository::findAll);
        model.addAttribute("authors", authors);
        return ("allAuthors");
    }
}
