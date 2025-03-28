package app.library.controller;

import app.library.model.Author;
import app.library.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books/creation/form")
public class AddBookFormController {

    private final Logger logger = LoggerFactory.getLogger(AddBookFormController.class);

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    protected String getAuthors(Model model) {
        logger.info("Get list of authors");
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        if (authors.isEmpty()) {
            logger.info("Authors are empty. Redirecting for creation.");
            return "addEmptyAuthor";
        }
        logger.info("Redirecting for books displaying");
        return "addBook";
    }
}
