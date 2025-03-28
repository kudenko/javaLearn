package app.library.controller;

import app.library.exceptions.AuthorRepositoryException;
import app.library.exceptions.BookRepositoryException;
import app.library.model.Author;
import app.library.repository.AuthorRepository;
import app.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/books/creation")
public class AddBookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorRepository authorRepository;

    private final Logger logger = LoggerFactory.getLogger(AddBookController.class);

    @PostMapping
    protected String createBook(@RequestParam String name,
                                @RequestParam String countPages,
                                @RequestParam String authorId,
                                Model model) {
        int intCountPages = Integer.parseInt(countPages);
        long longAuthorId = Long.parseLong(authorId);
        logger.info("Request parameters: name: {}, countPages: {}", name, countPages);
        try {
            bookService.addBook(name, intCountPages, longAuthorId);
            model.addAttribute("success", "Book Was successfully Added!!! You can add another one.");
            List<Author> authors = authorRepository.findAll();
            model.addAttribute("authors", authors);
        } catch (AuthorRepositoryException | BookRepositoryException e) {
            logger.info("error {}", e.toString());
            model.addAttribute("error", "Error, while adding a Book. Please try again or contact administrator");
        }
        logger.info("Redirecting to form");
        return "addBook";
    }
}
