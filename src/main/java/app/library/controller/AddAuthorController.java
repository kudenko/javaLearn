package app.library.controller;

import app.library.model.Author;
import app.library.exceptions.AuthorRepositoryException;
import app.library.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/authors/creation")
public class AddAuthorController {

    @Autowired
    private AuthorRepository authorRepository;
    private final Logger logger = LoggerFactory.getLogger(AddAuthorController.class);

    @PostMapping
    protected String createAuthor(@RequestParam String name, @RequestParam String lastname, @RequestParam String email, Model model) {
        logger.info("Request with parameters name: {}, lastName: {}, email {}", name, lastname, email);
        try {
            authorRepository.save(new Author(name, lastname, email));
            model.addAttribute("success", "Author Was successfully Added!!! You can add another one.");
        } catch (AuthorRepositoryException e) {
            logger.error(e.toString());
            model.addAttribute("error", "Error, while adding an Author. Please try again or contact administrator");
        }
        logger.info("Redirecting");
        return "addAuthor";
    }
}
