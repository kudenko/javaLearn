package app.library.controller;

import app.library.exceptions.AuthorRepositoryException;
import app.library.model.Author;
import app.library.service.AuthorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
        ModelAndView mav = new ModelAndView("addAuthor");
        mav.addObject("author", new Author());
        return mav;
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
        ModelAndView mav = new ModelAndView("findAuthor");
        mav.addObject("author", new Author());
        return mav;
    }

    @PostMapping("/creation")
    protected ModelAndView createAuthor(@ModelAttribute("author") @Valid Author author, BindingResult result) {
        ModelAndView mav = new ModelAndView("addAuthor");

        if (result.hasErrors()) {
            return mav;
        }

        logger.info("Request with parameters name: {}, lastName: {}, email {}", author.getFirstName(), author.getLastName(), author.getEmail());
        try {
            authorService.addAuthor(author);
            mav.addObject("success", "Author Was successfully Added!!! You can add another one.");
        } catch (AuthorRepositoryException e) {
            logger.error(e.toString());
            mav.addObject("error", "Error, while adding an Author. Please try again or contact administrator");
        }
        logger.info("Redirecting");
        return mav;
    }
}
