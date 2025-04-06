package app.library.controller;

import app.library.exceptions.AuthorRepositoryException;
import app.library.model.Author;
import app.library.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    private final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @GetMapping("/creation/form")
    protected String getAuthorsCreationForm() {
        return "addAuthor";
    }

    @GetMapping
    protected String getAuthors(@RequestParam(required = false) String email, Model model) {
        List<Author> authors = authorService.getAuthors(email);
        model.addAttribute("authors", authors);
        return ("allAuthors");
    }

    @GetMapping("/search/form")
    protected String getFindAuthorForm() {
        return "findAuthor";
    }

    @PostMapping("/creation")
    protected String createAuthor(@RequestParam String name, @RequestParam String lastname, @RequestParam String email, Model model) {
        logger.info("Request with parameters name: {}, lastName: {}, email {}", name, lastname, email);
        try {
            authorService.addAuthor(name, lastname, email);
            model.addAttribute("success", "Author Was successfully Added!!! You can add another one.");
        } catch (AuthorRepositoryException e) {
            logger.error(e.toString());
            model.addAttribute("error", "Error, while adding an Author. Please try again or contact administrator");
        }
        logger.info("Redirecting");
        return "addAuthor";
    }
}
