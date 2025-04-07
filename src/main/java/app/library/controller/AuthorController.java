package app.library.controller;

import app.library.exceptions.AuthorRepositoryException;
import app.library.model.Author;
import app.library.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    private final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @GetMapping("/creation/form")
    protected ModelAndView getAuthorsCreationForm() {
        return new ModelAndView("addAuthor");
    }

    @GetMapping
    protected ModelAndView getAuthors(@RequestParam(required = false) String email) {
        List<Author> authors = authorService.getAuthors(email);
        ModelAndView mav = new ModelAndView("allAuthors");
        mav.addObject("authors", authors);
        return mav;
    }

    @GetMapping("/search/form")
    protected ModelAndView getFindAuthorForm() {
        return new ModelAndView("findAuthor");
    }

    @PostMapping("/creation")
    protected ModelAndView createAuthor(@RequestParam String name, @RequestParam String lastname, @RequestParam String email) {
        ModelAndView mav = new ModelAndView("addAuthor");
        logger.info("Request with parameters name: {}, lastName: {}, email {}", name, lastname, email);
        try {
            authorService.addAuthor(name, lastname, email);
            mav.addObject("success", "Author Was successfully Added!!! You can add another one.");
        } catch (AuthorRepositoryException e) {
            logger.error(e.toString());
            mav.addObject("error", "Error, while adding an Author. Please try again or contact administrator");
        }
        logger.info("Redirecting");
        return mav;
    }
}
