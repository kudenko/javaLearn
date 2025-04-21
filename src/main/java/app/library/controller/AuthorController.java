package app.library.controller;

import app.library.model.Author;
import app.library.service.AuthorService;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping("/creation")
    protected ModelAndView getAuthorsCreationForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("addAuthor");
        request.setAttribute("viewName", "addAuthor");
        mav.addObject("author", new Author());
        return mav;
    }

    @GetMapping
    protected ModelAndView getAuthors(@RequestParam(required = false) String email, HttpServletRequest request) {
        List<Author> authors = authorService.getAuthors(email);
        ModelAndView mav = new ModelAndView("allAuthors");
        request.setAttribute("viewName", "allAuthors");
        mav.addObject("authors", authors);
        return mav;
    }

    @GetMapping("/search")
    protected ModelAndView getFindAuthorForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("findAuthor");
        request.setAttribute("viewName", "findAuthor");
        mav.addObject("author", new Author());
        return mav;
    }

    @PostMapping
    protected ModelAndView createAuthor(@ModelAttribute("author") @Valid Author author, BindingResult result, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("addAuthor");
        request.setAttribute("viewName", "addAuthor");
        if (result.hasErrors()) {
            return mav;
        }

        logger.info("Request with parameters name: {}, lastName: {}, email {}", author.getFirstName(), author.getLastName(), author.getEmail());
        authorService.addAuthor(author);
        mav.addObject("success", "Author Was successfully Added!!! You can add another one.");
        logger.info("Redirecting");
        return mav;
    }
}
