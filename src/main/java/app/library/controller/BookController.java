package app.library.controller;

import app.library.exceptions.AuthorRepositoryException;
import app.library.exceptions.BookRepositoryException;
import app.library.model.Author;
import app.library.model.Book;
import app.library.service.AuthorService;
import app.library.service.BookService;
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
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @PostMapping("/creation")
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
            List<Author> authors = authorService.getAuthors();
            model.addAttribute("authors", authors);
        } catch (AuthorRepositoryException | BookRepositoryException e) {
            logger.info("error {}", e.toString());
            model.addAttribute("error", "Error, while adding a Book. Please try again or contact administrator");
        }
        logger.info("Redirecting to form");
        return "addBook";
    }

    @GetMapping("/creation/form")
    protected String getAuthors(Model model) {
        logger.info("Get list of authors");
        List<Author> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        if (authors.isEmpty()) {
            logger.info("Authors are empty. Redirecting for creation.");
            return "addEmptyAuthor";
        }
        logger.info("Redirecting for books displaying");
        return "addBook";
    }

    @GetMapping
    protected String getAllBooks(@RequestParam(required = false) String name, Model model) {
        List<Book> books = bookService.getBooks(name);
        model.addAttribute("books", books);
        return "allBooks";
    }

    @GetMapping("/search/form")
    protected String getBookFindForm() {
        return "findBook";
    }
}
